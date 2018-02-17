package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

@Properties(target = "org.bytedeco.javacpp.bmddecklink",
            value = {@Platform(include = {"<Unknwnbase.h>", "<DeckLinkAPI_h.h>"}),
                        @Platform(value = "windows-x86_64",
                                  includepath = {
                                         "C:/SDK/Blackmagic DeckLink SDK 10.9.10/windows-x86_64",
                                         "C:/Program Files (x86)/Windows Kits/10/Include/10.0.16299.0/um"},
                                  linkpath = {
                                         "C:/Program Files (x86)/Windows Kits/10/Lib/10.0.16299.0/um/x64"})})
public class bmddecklink implements InfoMapper
{
    public void map(InfoMap infoMap)
    {
        infoMap.put(new Info("_In_reads_")
                .cppTypes()
                .annotations());
    }
}