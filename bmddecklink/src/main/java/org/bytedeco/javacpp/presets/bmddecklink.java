package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(target="org.bytedeco.javacpp.bmddecklink", value={
        @Platform(value="windows",
                  include = {
                        "Unknwnbase.h", "<DeckLinkAPI_h.h>"
                  },
                  includepath = {
                        "C:\\Program Files (x86)\\Windows Kits\\10\\Include\\10.0.16299.0\\um"
                  },
                  link = {
                        "comsuppw", "ole32", "oleaut32", "uuid"
                  })
})
public class bmddecklink implements InfoMapper
{
    public void map(InfoMap infoMap)
    {
        infoMap.put(new Info("long", "unsigned long")
                .cast()
                .valueTypes("int")
                .pointerTypes("IntPointer", "IntBuffer", "int[]"));
    }
}

