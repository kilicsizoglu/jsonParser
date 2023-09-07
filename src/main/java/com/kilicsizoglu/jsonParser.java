package com.kilicsizoglu;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

public class jsonParser {
    private int i = 0;
    private int mod = -1;
    private char ch = (char) -1;
    private int objectSize = 0;
    private String data = "";
    private boolean processBlock = false;
    private boolean arrayProcessBlock = false;
    private boolean StringReadMode = false;
    private String objectData = "";
    private List<String> listData;
    public Map<String, Object> jsonData;
    private StringReader reader;
    private StringBuffer buffer;

    public jsonParser(String json) {
        jsonData = new java.util.HashMap<String, Object>();
        reader = new StringReader(json.trim());
        buffer = new StringBuffer();
        listData = new java.util.ArrayList<String>();
    }

    public Map<String, Object> parse() throws IOException {
        while (true) {
            int c = reader.read();
            if (c == -1) {
                break;
            }
            ch = (char) c;
            switch (ch) {
                case '}':
                    // obje modu kapat mod = 0
                    mod0();
                    break;
                case '{':
                    // obje modu ac mod = 1
                    mod1();
                    break;
                case '[':
                    // dizi modu ac mod = 2
                    mod2();
                    break;
                case ']':
                    // dizi modu kapat mod = 3
                    mod3();
                    break;
                case ',':
                    // yeni eleman modu ac mod = 4
                    mod4();
                    break;
                case ':':
                    // devam et mod = 5
                    mod5();
                    break;
                case '"':
                    // string modu ac ve aciksa kapat mod = 6
                    mod6();
                    break;
                case ' ':
                    // data okuma modu haric bosluklari atla mod = 7
                    mod7();
                    break;
                case '\n':
                    // data okuma modu haric satir atlama karakterlerini atla mod = 8
                    mod8();
                    break;
                case '\r':
                    // data okuma modu haric satir atlama karakterlerini atla mod = 9
                    mod9();
                    break;
                case '\t':
                    // data okuma modu haric tab karakterlerini atla mod = 10
                    mod10();
                    break;
                default:
                    // data okuma veri okuma ac
                    modDafault();

            }
        }
        return jsonData;
    }

    public void mod0() {
        mod = 0;
        objectSize--;
        if (processBlock == true) {
            buffer.append(ch);
        }
        if (objectSize == 0) {
            processBlock = false;
        }
        if (objectSize == 0 && processBlock == false) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        if (objectSize == 0) {
            if (data == "") {
                jsonData.put(String.valueOf(i), buffer.toString());
                i++;
            } else {
                jsonData.put(data, buffer.toString());
            }
        }
        if (processBlock != true && objectSize == 0) {
            buffer = new StringBuffer();
        }
    }

    public void mod1() {
        mod = 1;
        objectSize++;
        if (processBlock == true) {
            buffer.append(ch);
        }
        if (processBlock != true) {
            processBlock = true;
        }
    }

    // dizi modu
    public void mod2() {
        mod = 2;
        if (processBlock == true) {
            buffer.append(ch);
        }
        //  else if (arrayProcessBlock != true) {
        //     arrayProcessBlock = true;
        //     objectSize++;
        // }
    }

    public void mod3() {
        mod = 3;
        if (processBlock == true) {
            buffer.append(ch);
        } 
        // objectSize--;
        // if (arrayProcessBlock == true && objectSize == 0) {
        //     arrayProcessBlock = false;
        // }
        // if (objectSize == 0 && arrayProcessBlock == true) {
        //     jsonData.put(objectData, listData);
        // }
    }

    public void mod4() {
        if (processBlock == true) {
            buffer.append(ch);
        }
    }

    public void mod5() {
        mod = 5;
        if (objectSize > 1 || processBlock == true) {
            buffer.append(ch);
        }
    }

    // todo : degisken degerini json data ya ekle ve buffer bos olma sorununu coz
    public void mod6() {
        // if (arrayProcessBlock == false) {
        //     listData.add(data);
        //     buffer = new StringBuffer();
        // }
        if (mod == 11 && processBlock != true && StringReadMode != true) {
            data = buffer.toString();
            buffer = new StringBuffer();
        }
        // if (StringReadMode == true && processBlock != true) {
        //     StringReadMode = false;
        // }
        // if (mod == 5 && processBlock == false && arrayProcessBlock == false) {
        //     StringReadMode = true;
        // }
        mod = 6;
        if (processBlock == true) {
            buffer.append(ch);
        }
        // if (StringReadMode == true) {
        //     objectData = buffer.toString();
        //     jsonData.put(data, objectData);
        //     buffer = new StringBuffer();
        // }
    }

    public void mod7() {
        mod = 7;
        if (processBlock == true) {
            buffer.append(ch);
        }
    }

    public void mod8() {
        mod = 8;
        if (processBlock == true) {
            buffer.append(ch);
        }
    }

    public void mod9() {
        mod = 9;
        if (processBlock == true) {
            buffer.append(ch);
        }
    }

    public void mod10() {
        mod = 10;
        if (processBlock == true) {
            buffer.append(ch);
        }
    }

    public void modDafault() {
        mod = 11;
        buffer.append(ch);

    }

}
