#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" bmddecklink
    popd
    exit
fi

rm -rf $PLATFORM
mkdir -p $PLATFORM
cd $PLATFORM
INSTALL_PATH=`pwd`

SDK_VERSION=10.9.10

case $PLATFORM in
    windows-*)
        SDK_LOCATION="/C/SDK/Blackmagic DeckLink SDK $SDK_VERSION/"
        if [[ ! -d $SDK_LOCATION ]]; then
            echo "Please install Decklink SDK under the default installation directory"
            exit 1
        fi

        INPUT_IDL="$SDK_LOCATION/Win/Include/DeckLinkAPI.idl"

        # Step 1: Compile IDL files
        midl.exe -env x64 -h "DeckLinkAPI_h.h" -W1 -char signed -target "NT60" -nologo "$INPUT_IDL"

        # Step 2: Create DEF file by extracting interface names.
        OUTPUT_C="$INSTALL_PATH/DeckLinkAPI_i.c"
        OUTPUT_DEF="$INSTALL_PATH/DeckLinkAPI.def"

        echo "LIBRARY    DECKLINK" > "$OUTPUT_DEF"
        echo "EXPORTS" >> "$OUTPUT_DEF"

        regex="MIDL_DEFINE_GUID\((.*),\s?(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),"
        while IFS='' read -r line || [[ -n "$line" ]]; do
            if [[ $line =~ $regex ]] && [[ $line == MIDL* ]]; then
                echo "    ${BASH_REMATCH[2]}" >> "$OUTPUT_DEF"
            fi
        done < "$OUTPUT_C"

        # Step 3: Use the DEF and C file to create a DLL with appropriate bindings.
        cl "$OUTPUT_C" -link -DLL -out:DeckLinkAPI.dll -DEF:"$OUTPUT_DEF"

        OUTPUT_LOCATION="$SDK_LOCATION/$PLATFORM"
        rm -rf "$OUTPUT_LOCATION"
        mkdir -p "$OUTPUT_LOCATION"
        cp -r "$INSTALL_PATH/." "$OUTPUT_LOCATION"
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd ../..