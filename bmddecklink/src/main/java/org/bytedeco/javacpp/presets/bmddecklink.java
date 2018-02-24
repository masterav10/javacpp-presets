package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(names = {"windows-x86_64"},
            target = "org.bytedeco.javacpp.bmddecklink",
            value = {@Platform(include = {"<DeckLinkAPI_h.h>", "<DeckLinkAPI_i.c>"}),
                        @Platform(value = "windows-x86_64",
                                  link = {
                                        "ole32", "oleaut32", "amstrmid", "strmiids", "uuid"
                                  })})
public class bmddecklink implements InfoMapper
{
    public void map(InfoMap infoMap)
    {
        infoMap.put(new Info("DeckLinkAPI_i.c").skip())
               .put(new Info("_WIN32_WINNT").cppTypes().define(false))
               .put(new Info("std::vector<std::string>").pointerTypes("StringVector").define())
               .put(new Info("GUID").cast().pointerTypes("Pointer"))
               .put(new Info("long", "unsigned long").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"));
    }
}