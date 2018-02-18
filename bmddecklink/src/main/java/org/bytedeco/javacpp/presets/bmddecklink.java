package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(names = {"windows-x86_64"},
            target = "org.bytedeco.javacpp.bmddecklink",
            value = {@Platform(include = {"Unknwnbase.h", "<DeckLinkAPI_h.h>"}),
                        @Platform(value = "windows-x86_64",
                                  includepath = {
                                        "C:/Program Files (x86)/Windows Kits/10/Include/10.0.16299.0/um"},
                                  linkpath = {
                                        "C:/Program Files (x86)/Windows Kits/10/Lib/10.0.16299.0/um/x64"})})
public class bmddecklink implements InfoMapper
{
    public void map(InfoMap infoMap)
    {
        infoMap
                .put(new Info("IID_IUnknown")
                        .valueTypes("IUnknown")
                        .pointerTypes("@ByPtrPtr IUnknown"));
    }

}