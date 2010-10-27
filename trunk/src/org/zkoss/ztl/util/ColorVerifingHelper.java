package org.zkoss.ztl.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Phoenix
 *
 */
public class ColorVerifingHelper {

	private static ColorVerifingHelper instance;
	private static final String TEMP_HEX = "^\\#[0-9a-fA-F]+[0-9a-fA-F]+[0-9a-fA-F]+";
	private static final String BYTE_NUMBER = "((1?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
	private static final String TEMP_RGB = "^(?i)[r][g][b]\\(" + BYTE_NUMBER +"," +
														" *" + BYTE_NUMBER +"," +
														" *" + BYTE_NUMBER +"\\)";
	private static final String TEMP_NAMED_COLOR = "[a-zA-Z]+";
	private static final Map<String, String> commonColorMap = new HashMap<String, String>();

	static {
		// You can refer this link to improve color map.
		// http://web.njit.edu/~kevin/rgb.txt.html
		commonColorMap.put("red", "ff0000");
		commonColorMap.put("green", "00ff00");
		commonColorMap.put("blue", "0000ff");
		commonColorMap.put("Grey", "545454");
		commonColorMap.put("grey", "BEBEBE");
		commonColorMap.put("LightGray", "D3D3D3");
		commonColorMap.put("LightSlateGrey", "778899");
		commonColorMap.put("SlateGray", "708090");
		commonColorMap.put("SlateGray1", "C6E2FF");
		commonColorMap.put("SlateGray2", "B9D3EE");
		commonColorMap.put("SlateGray3", "9FB6CD");
		commonColorMap.put("SlateGray4", "6C7B8B");
		commonColorMap.put("black", "000000");
		commonColorMap.put("grey0", "000000");
		commonColorMap.put("grey1", "030303");
		commonColorMap.put("grey2", "050505");
		commonColorMap.put("grey3", "080808");
		commonColorMap.put("grey4", "0A0A0A");
		commonColorMap.put("grey5", "0D0D0D");
		commonColorMap.put("grey6", "0F0F0F");
		commonColorMap.put("grey7", "121212");
		commonColorMap.put("grey8", "141414");
		commonColorMap.put("grey9", "171717");
		commonColorMap.put("grey10", "1A1A1A");
		commonColorMap.put("grey11", "1C1C1C");
		commonColorMap.put("grey12", "1F1F1F");
		commonColorMap.put("grey13", "212121");
		commonColorMap.put("grey14", "242424");
		commonColorMap.put("grey15", "262626");
		commonColorMap.put("grey16", "292929");
		commonColorMap.put("grey17", "2B2B2B");
		commonColorMap.put("grey18", "2E2E2E");
		commonColorMap.put("grey19", "303030");
		commonColorMap.put("grey20", "333333");
		commonColorMap.put("grey21", "363636");
		commonColorMap.put("grey22", "383838");
		commonColorMap.put("grey23", "3B3B3B");
		commonColorMap.put("grey24", "3D3D3D");
		commonColorMap.put("grey25", "404040");
		commonColorMap.put("grey26", "424242");
		commonColorMap.put("grey27", "454545");
		commonColorMap.put("grey28", "474747");
		commonColorMap.put("grey29", "4A4A4A");
		commonColorMap.put("grey30", "4D4D4D");
		commonColorMap.put("grey31", "4F4F4F");
		commonColorMap.put("grey32", "525252");
		commonColorMap.put("grey33", "545454");
		commonColorMap.put("grey34", "575757");
		commonColorMap.put("grey35", "595959");
		commonColorMap.put("grey36", "5C5C5C");
		commonColorMap.put("grey37", "5E5E5E");
		commonColorMap.put("grey38", "616161");
		commonColorMap.put("grey39", "636363");
		commonColorMap.put("grey40", "666666");
		commonColorMap.put("grey42", "6B6B6B");
		commonColorMap.put("grey43", "6E6E6E");
		commonColorMap.put("grey44", "707070");
		commonColorMap.put("grey45", "737373");
		commonColorMap.put("grey46", "757575");
		commonColorMap.put("grey47", "787878");
		commonColorMap.put("grey48", "7A7A7A");
		commonColorMap.put("grey49", "7D7D7D");
		commonColorMap.put("grey50", "7F7F7F");
		commonColorMap.put("grey51", "828282");
		commonColorMap.put("grey52", "858585");
		commonColorMap.put("grey53", "878787");
		commonColorMap.put("grey54", "8A8A8A");
		commonColorMap.put("grey55", "8C8C8C");
		commonColorMap.put("grey56", "8F8F8F");
		commonColorMap.put("grey57", "919191");
		commonColorMap.put("grey58", "949494");
		commonColorMap.put("grey59", "969696");
		commonColorMap.put("grey60", "999999");
		commonColorMap.put("grey61", "9C9C9C");
		commonColorMap.put("grey62", "9E9E9E");
		commonColorMap.put("grey63", "A1A1A1");
		commonColorMap.put("grey64", "A3A3A3");
		commonColorMap.put("grey65", "A6A6A6");
		commonColorMap.put("grey66", "A8A8A8");
		commonColorMap.put("grey67", "ABABAB");
		commonColorMap.put("grey68", "ADADAD");
		commonColorMap.put("grey69", "B0B0B0");
		commonColorMap.put("grey70", "B3B3B3");
		commonColorMap.put("grey71", "B5B5B5");
		commonColorMap.put("grey72", "B8B8B8");
		commonColorMap.put("grey73", "BABABA");
		commonColorMap.put("grey74", "BDBDBD");
		commonColorMap.put("grey75", "BFBFBF");
		commonColorMap.put("grey76", "C2C2C2");
		commonColorMap.put("grey77", "C4C4C4");
		commonColorMap.put("grey78", "C7C7C7");
		commonColorMap.put("grey79", "C9C9C9");
		commonColorMap.put("grey80", "CCCCCC");
		commonColorMap.put("grey81", "CFCFCF");
		commonColorMap.put("grey82", "D1D1D1");
		commonColorMap.put("grey83", "D4D4D4");
		commonColorMap.put("grey84", "D6D6D6");
		commonColorMap.put("grey85", "D9D9D9");
		commonColorMap.put("grey86", "DBDBDB");
		commonColorMap.put("grey87", "DEDEDE");
		commonColorMap.put("grey88", "E0E0E0");
		commonColorMap.put("grey89", "E3E3E3");
		commonColorMap.put("grey90", "E5E5E5");
		commonColorMap.put("grey91", "E8E8E8");
		commonColorMap.put("grey92", "EBEBEB");
		commonColorMap.put("grey93", "EDEDED");
		commonColorMap.put("grey94", "F0F0F0");
		commonColorMap.put("grey95", "F2F2F2");
		commonColorMap.put("grey96", "F5F5F5");
		commonColorMap.put("grey97", "F7F7F7");
		commonColorMap.put("grey98", "FAFAFA");
		commonColorMap.put("grey99", "FCFCFC");
		commonColorMap.put("grey100, White", "FFFFFF");
		commonColorMap.put("Dark Slate Grey", "2F4F4F");
		commonColorMap.put("Dim Grey", "545454");
		commonColorMap.put("Light Grey [sic]", "DBDB70");
		commonColorMap.put("Very Light Grey", "CDCDCD");
		commonColorMap.put("Free Speech Grey", "635688");
		commonColorMap.put("AliceBlue", "F0F8FF");
		commonColorMap.put("BlueViolet", "8A2BE2");
		commonColorMap.put("Cadet Blue", "5F9F9F");
		commonColorMap.put("CadetBlue", "5F9EA0");
		commonColorMap.put("CadetBlue", "5F9EA0");
		commonColorMap.put("CadetBlue1", "98F5FF");
		commonColorMap.put("CadetBlue2", "8EE5EE");
		commonColorMap.put("CadetBlue3", "7AC5CD");
		commonColorMap.put("CadetBlue4", "53868B");
		commonColorMap.put("Corn Flower Blue", "42426F");
		commonColorMap.put("CornflowerBlue", "6495ED");
		commonColorMap.put("DarkSlateBlue", "483D8B");
		commonColorMap.put("DarkTurquoise", "00CED1");
		commonColorMap.put("DeepSkyBlue", "00BFFF");
		commonColorMap.put("DeepSkyBlue1", "00BFFF");
		commonColorMap.put("DeepSkyBlue2", "00B2EE");
		commonColorMap.put("DeepSkyBlue3", "009ACD");
		commonColorMap.put("DeepSkyBlue4", "00688B");
		commonColorMap.put("DodgerBlue", "1E90FF");
		commonColorMap.put("DodgerBlue1", "1E90FF");
		commonColorMap.put("DodgerBlue2", "1C86EE");
		commonColorMap.put("DodgerBlue3", "1874CD");
		commonColorMap.put("DodgerBlue4", "104E8B");
		commonColorMap.put("LightBlue", "ADD8E6");
		commonColorMap.put("LightBlue1", "BFEFFF");
		commonColorMap.put("LightBlue2", "B2DFEE");
		commonColorMap.put("LightBlue3", "9AC0CD");
		commonColorMap.put("LightBlue4", "68838B");
		commonColorMap.put("LightCyan", "E0FFFF");
		commonColorMap.put("LightCyan1", "E0FFFF");
		commonColorMap.put("LightCyan2", "D1EEEE");
		commonColorMap.put("LightCyan3", "B4CDCD");
		commonColorMap.put("LightCyan4", "7A8B8B");
		commonColorMap.put("LightSkyBlue", "87CEFA");
		commonColorMap.put("LightSkyBlue1", "B0E2FF");
		commonColorMap.put("LightSkyBlue2", "A4D3EE");
		commonColorMap.put("LightSkyBlue3", "8DB6CD");
		commonColorMap.put("LightSkyBlue4", "607B8B");
		commonColorMap.put("LightSlateBlue", "8470FF");
		commonColorMap.put("LightSteelBlue", "B0C4DE");
		commonColorMap.put("LightSteelBlue1", "CAE1FF");
		commonColorMap.put("LightSteelBlue2", "BCD2EE");
		commonColorMap.put("LightSteelBlue3", "A2B5CD");
		commonColorMap.put("LightSteelBlue4", "6E7B8B");
		commonColorMap.put("Aquamarine", "70DB93");
		commonColorMap.put("MediumBlue", "0000CD");
		commonColorMap.put("MediumSlateBlue", "7B68EE");
		commonColorMap.put("MediumTurquoise", "48D1CC");
		commonColorMap.put("MidnightBlue", "191970");
		commonColorMap.put("NavyBlue", "000080");
		commonColorMap.put("PaleTurquoise", "AFEEEE");
		commonColorMap.put("PaleTurquoise1", "BBFFFF");
		commonColorMap.put("PaleTurquoise2", "AEEEEE");
		commonColorMap.put("PaleTurquoise3", "96CDCD");
		commonColorMap.put("PaleTurquoise4", "668B8B");
		commonColorMap.put("PowderBlue", "B0E0E6");
		commonColorMap.put("RoyalBlue", "4169E1");
		commonColorMap.put("RoyalBlue1", "4876FF");
		commonColorMap.put("RoyalBlue2", "436EEE");
		commonColorMap.put("RoyalBlue3", "3A5FCD");
		commonColorMap.put("RoyalBlue4", "27408B");
		commonColorMap.put("RoyalBlue5", "002266");
		commonColorMap.put("SkyBlue", "87CEEB");
		commonColorMap.put("SkyBlue1", "87CEFF");
		commonColorMap.put("SkyBlue2", "7EC0EE");
		commonColorMap.put("SkyBlue3", "6CA6CD");
		commonColorMap.put("SkyBlue4", "4A708B");
		commonColorMap.put("SlateBlue1", "836FFF");
		commonColorMap.put("SlateBlue2", "7A67EE");
		commonColorMap.put("SlateBlue3", "6959CD");
		commonColorMap.put("SlateBlue4", "473C8B");
		commonColorMap.put("SteelBlue", "4682B4");
		commonColorMap.put("SteelBlue1", "63B8FF");
		commonColorMap.put("SteelBlue2", "5CACEE");
		commonColorMap.put("SteelBlue3", "4F94CD");
		commonColorMap.put("SteelBlue4", "36648B");
		commonColorMap.put("aquamarine", "7FFFD4");
		commonColorMap.put("aquamarine1", "7FFFD4");
		commonColorMap.put("aquamarine2", "76EEC6");
		commonColorMap.put("aquamarine4", "458B74");
		commonColorMap.put("azure", "F0FFFF");
		commonColorMap.put("azure1", "F0FFFF");
		commonColorMap.put("azure2", "E0EEEE");
		commonColorMap.put("azure3", "C1CDCD");
		commonColorMap.put("azure4", "838B8B");
		commonColorMap.put("blue", "0000FF");
		commonColorMap.put("blue1", "0000FF");
		commonColorMap.put("blue2", "0000EE");
		commonColorMap.put("blue3", "0000CD");
		commonColorMap.put("blue4", "00008B");
		commonColorMap.put("aqua", "00FFFF");
		commonColorMap.put("cyan", "00FFFF");
		commonColorMap.put("cyan1", "00FFFF");
		commonColorMap.put("cyan2", "00EEEE");
		commonColorMap.put("cyan3", "00CDCD");
		commonColorMap.put("cyan4", "008B8B");
		commonColorMap.put("navy", "000080");
		commonColorMap.put("teal", "008080");
		commonColorMap.put("turquoise", "40E0D0");
		commonColorMap.put("turquoise1", "00F5FF");
		commonColorMap.put("turquoise2", "00E5EE");
		commonColorMap.put("turquoise3", "00C5CD");
		commonColorMap.put("turquoise4", "00868B");
		commonColorMap.put("DarkSlateGray", "2F4F4F");
		commonColorMap.put("DarkSlateGray1", "97FFFF");
		commonColorMap.put("DarkSlateGray2", "8DEEEE");
		commonColorMap.put("DarkSlateGray3", "79CDCD");
		commonColorMap.put("DarkSlateGray4", "528B8B");
		commonColorMap.put("Dark Slate Blue", "241882");
		commonColorMap.put("Dark Turquoise", "7093DB");
		commonColorMap.put("Light Blue [sic]", "CD7F32");
		commonColorMap.put("Medium Blue [sic]", "CD7F32");
		commonColorMap.put("Medium Slate Blue", "7F00FF");
		commonColorMap.put("Medium Turquoise", "70DBDB");
		commonColorMap.put("Midnight Blue", "2F2F4F");
		commonColorMap.put("Navy Blue", "23238E");
		commonColorMap.put("Neon Blue", "4D4DFF");
		commonColorMap.put("New Midnight Blue", "00009C");
		commonColorMap.put("Rich Blue", "5959AB");
		commonColorMap.put("Sky Blue", "3299CC");
		commonColorMap.put("Slate Blue", "007FFF");
		commonColorMap.put("Summer Sky", "38B0DE");
		commonColorMap.put("Iris Blue", "03B4C8");
		commonColorMap.put("Free Speech Blue", "4156C5");
		commonColorMap.put("RosyBrown", "BC8F8F");
		commonColorMap.put("RosyBrown1", "FFC1C1");
		commonColorMap.put("RosyBrown2", "EEB4B4");
		commonColorMap.put("RosyBrown3", "CD9B9B");
		commonColorMap.put("RosyBrown4", "8B6969");
		commonColorMap.put("SaddleBrown", "8B4513");
		commonColorMap.put("SandyBrown", "F4A460");
		commonColorMap.put("beige", "F5F5DC");
		commonColorMap.put("brown", "A52A2A");
		commonColorMap.put("brown", "A62A2A");
		commonColorMap.put("brown1", "FF4040");
		commonColorMap.put("brown2", "EE3B3B");
		commonColorMap.put("brown3", "CD3333");
		commonColorMap.put("brown4", "8B2323");
		commonColorMap.put("dark brown", "5C4033");
		commonColorMap.put("burlywood", "DEB887");
		commonColorMap.put("burlywood1", "FFD39B");
		commonColorMap.put("burlywood2", "EEC591");
		commonColorMap.put("burlywood3", "CDAA7D");
		commonColorMap.put("burlywood4", "8B7355");
		commonColorMap.put("baker's chocolate", "5C3317");
		commonColorMap.put("chocolate", "D2691E");
		commonColorMap.put("chocolate1", "FF7F24");
		commonColorMap.put("chocolate2", "EE7621");
		commonColorMap.put("chocolate3", "CD661D");
		commonColorMap.put("chocolate4", "8B4513");
		commonColorMap.put("peru", "CD853F");
		commonColorMap.put("tan", "D2B48C");
		commonColorMap.put("tan1", "FFA54F");
		commonColorMap.put("tan2", "EE9A49");
		commonColorMap.put("tan3", "CD853F");
		commonColorMap.put("tan4", "8B5A2B");
		commonColorMap.put("Dark Tan", "97694F");
		commonColorMap.put("Dark Wood", "855E42");
		commonColorMap.put("Light Wood", "856363");
		commonColorMap.put("Medium Wood", "A68064");
		commonColorMap.put("New Tan", "EBC79E");
		commonColorMap.put("Semi-Sweet Chocolate", "6B4226");
		commonColorMap.put("Sienna", "8E6B23");
		commonColorMap.put("Tan", "DB9370");
		commonColorMap.put("Very Dark Brown", "5C4033");
		commonColorMap.put("Dark Green", "2F4F2F");
		commonColorMap.put("DarkGreen", "006400");
		commonColorMap.put("dark green copper", "4A766E");
		commonColorMap.put("DarkKhaki", "BDB76B");
		commonColorMap.put("DarkOliveGreen", "556B2F");
		commonColorMap.put("DarkOliveGreen1", "CAFF70");
		commonColorMap.put("DarkOliveGreen2", "BCEE68");
		commonColorMap.put("DarkOliveGreen3", "A2CD5A");
		commonColorMap.put("DarkOliveGreen4", "6E8B3D");
		commonColorMap.put("olive", "808000");
		commonColorMap.put("DarkSeaGreen", "8FBC8F");
		commonColorMap.put("DarkSeaGreen1", "C1FFC1");
		commonColorMap.put("DarkSeaGreen2", "B4EEB4");
		commonColorMap.put("DarkSeaGreen3", "9BCD9B");
		commonColorMap.put("DarkSeaGreen4", "698B69");
		commonColorMap.put("ForestGreen", "228B22");
		commonColorMap.put("GreenYellow", "ADFF2F");
		commonColorMap.put("LawnGreen", "7CFC00");
		commonColorMap.put("LightSeaGreen", "20B2AA");
		commonColorMap.put("LimeGreen", "32CD32");
		commonColorMap.put("MediumSeaGreen", "3CB371");
		commonColorMap.put("MediumSpringGreen", "00FA9A");
		commonColorMap.put("MintCream", "F5FFFA");
		commonColorMap.put("OliveDrab", "6B8E23");
		commonColorMap.put("OliveDrab1", "C0FF3E");
		commonColorMap.put("OliveDrab2", "B3EE3A");
		commonColorMap.put("OliveDrab3", "9ACD32");
		commonColorMap.put("OliveDrab4", "698B22");
		commonColorMap.put("PaleGreen", "98FB98");
		commonColorMap.put("PaleGreen1", "9AFF9A");
		commonColorMap.put("PaleGreen2", "90EE90");
		commonColorMap.put("PaleGreen3", "7CCD7C");
		commonColorMap.put("PaleGreen4", "548B54");
		commonColorMap.put("SeaGreen1", "54FF9F");
		commonColorMap.put("SeaGreen2", "4EEE94");
		commonColorMap.put("SeaGreen3", "43CD80");
		commonColorMap.put("SpringGreen", "00FF7F");
		commonColorMap.put("SpringGreen1", "00FF7F");
		commonColorMap.put("SpringGreen2", "00EE76");
		commonColorMap.put("SpringGreen3", "00CD66");
		commonColorMap.put("SpringGreen4", "008B45");
		commonColorMap.put("YellowGreen", "9ACD32");
		commonColorMap.put("chartreuse", "7FFF00");
		commonColorMap.put("chartreuse1", "7FFF00");
		commonColorMap.put("chartreuse2", "76EE00");
		commonColorMap.put("chartreuse3", "66CD00");
		commonColorMap.put("chartreuse4", "458B00");
		commonColorMap.put("green", "00FF00");
		commonColorMap.put("green", "008000");
		commonColorMap.put("lime", "00FF00");
		commonColorMap.put("green1", "00FF00");
		commonColorMap.put("green2", "00EE00");
		commonColorMap.put("green3", "00CD00");
		commonColorMap.put("green4", "008B00");
		commonColorMap.put("khaki", "F0E68C");
		commonColorMap.put("khaki1", "FFF68F");
		commonColorMap.put("khaki2", "EEE685");
		commonColorMap.put("khaki3", "CDC673");
		commonColorMap.put("khaki4", "8B864E");
		commonColorMap.put("Dark Olive Green", "4F4F2F");
		commonColorMap.put("Green Yellow [sic]", "D19275");
		commonColorMap.put("Hunter Green [sic]", "8E2323");
		commonColorMap.put("Lime Green [sic]", "D19275");
		commonColorMap.put("Medium Forest Green", "DBDB70");
		commonColorMap.put("Medium Sea Green", "426F42");
		commonColorMap.put("Medium Spring Green", "7FFF00");
		commonColorMap.put("Pale Green", "8FBC8F");
		commonColorMap.put("Sea Green", "238E68");
		commonColorMap.put("Spring Green", "00FF7F");
		commonColorMap.put("Free Speech Green", "09F911");
		commonColorMap.put("Free Speech Aquamarine", "029D74");
		commonColorMap.put("DarkOrange", "FF8C00");
		commonColorMap.put("DarkOrange1", "FF7F00");
		commonColorMap.put("DarkOrange2", "EE7600");
		commonColorMap.put("DarkOrange3", "CD6600");
		commonColorMap.put("DarkOrange4", "8B4500");
		commonColorMap.put("DarkSalmon", "E9967A");
		commonColorMap.put("LightCoral", "F08080");
		commonColorMap.put("LightSalmon", "FFA07A");
		commonColorMap.put("LightSalmon1", "FFA07A");
		commonColorMap.put("LightSalmon2", "EE9572");
		commonColorMap.put("LightSalmon3", "CD8162");
		commonColorMap.put("LightSalmon4", "8B5742");
		commonColorMap.put("PeachPuff", "FFDAB9");
		commonColorMap.put("PeachPuff1", "FFDAB9");
		commonColorMap.put("PeachPuff2", "EECBAD");
		commonColorMap.put("PeachPuff3", "CDAF95");
		commonColorMap.put("PeachPuff4", "8B7765");
		commonColorMap.put("bisque", "FFE4C4");
		commonColorMap.put("bisque1", "FFE4C4");
		commonColorMap.put("bisque2", "EED5B7");
		commonColorMap.put("bisque3", "CDB79E");
		commonColorMap.put("bisque4", "8B7D6B");
		commonColorMap.put("coral", "FF7F00");
		commonColorMap.put("coral", "FF7F50");
		commonColorMap.put("coral1", "FF7256");
		commonColorMap.put("coral2", "EE6A50");
		commonColorMap.put("coral3", "CD5B45");
		commonColorMap.put("coral4", "8B3E2F");
		commonColorMap.put("honeydew", "F0FFF0");
		commonColorMap.put("honeydew1", "F0FFF0");
		commonColorMap.put("honeydew2", "E0EEE0");
		commonColorMap.put("honeydew3", "C1CDC1");
		commonColorMap.put("honeydew4", "838B83");
		commonColorMap.put("orange", "FFA500");
		commonColorMap.put("orange1", "FFA500");
		commonColorMap.put("orange2", "EE9A00");
		commonColorMap.put("orange3", "CD8500");
		commonColorMap.put("orange4", "8B5A00");
		commonColorMap.put("salmon", "FA8072");
		commonColorMap.put("salmon1", "FF8C69");
		commonColorMap.put("salmon2", "EE8262");
		commonColorMap.put("salmon3", "CD7054");
		commonColorMap.put("salmon4", "8B4C39");
		commonColorMap.put("sienna", "A0522D");
		commonColorMap.put("sienna1", "FF8247");
		commonColorMap.put("sienna2", "EE7942");
		commonColorMap.put("sienna3", "CD6839");
		commonColorMap.put("sienna4", "8B4726");
		commonColorMap.put("Mandarian Orange", "8E2323");
		commonColorMap.put("Orange", "FF7F00");
		commonColorMap.put("Orange Red", "FF2400");
		commonColorMap.put("DeepPink", "FF1493");
		commonColorMap.put("DeepPink1", "FF1493");
		commonColorMap.put("DeepPink2", "EE1289");
		commonColorMap.put("DeepPink3", "CD1076");
		commonColorMap.put("DeepPink4", "8B0A50");
		commonColorMap.put("HotPink", "FF69B4");
		commonColorMap.put("HotPink1", "FF6EB4");
		commonColorMap.put("HotPink2", "EE6AA7");
		commonColorMap.put("HotPink3", "CD6090");
		commonColorMap.put("HotPink4", "8B3A62");
		commonColorMap.put("PaleTurquoise", "AFEEEE");
		commonColorMap.put("PaleTurquoise1", "BBFFFF");
		commonColorMap.put("PaleTurquoise2", "AEEEEE");
		commonColorMap.put("PaleTurquoise3", "96CDCD");
		commonColorMap.put("PaleTurquoise4", "668B8B");
		commonColorMap.put("PowderBlue", "B0E0E6");
		commonColorMap.put("RoyalBlue", "4169E1");
		commonColorMap.put("RoyalBlue1", "4876FF");
		commonColorMap.put("RoyalBlue2", "436EEE");
		commonColorMap.put("RoyalBlue3", "3A5FCD");
		commonColorMap.put("RoyalBlue4", "27408B");
		commonColorMap.put("RoyalBlue5", "002266");
		commonColorMap.put("SkyBlue", "87CEEB");
		commonColorMap.put("SkyBlue1", "87CEFF");
		commonColorMap.put("SkyBlue2", "7EC0EE");
		commonColorMap.put("SkyBlue3", "6CA6CD");
		commonColorMap.put("SkyBlue4", "4A708B");
		commonColorMap.put("SlateBlue", "6A5ACD");
		commonColorMap.put("IndianRed", "CD5C5C");
		commonColorMap.put("IndianRed1", "FF6A6A");
		commonColorMap.put("IndianRed2", "EE6363");
		commonColorMap.put("IndianRed3", "CD5555");
		commonColorMap.put("IndianRed4", "8B3A3A");
		commonColorMap.put("LightPink", "FFB6C1");
		commonColorMap.put("LightPink1", "FFAEB9");
		commonColorMap.put("LightPink2", "EEA2AD");
		commonColorMap.put("LightPink3", "CD8C95");
		commonColorMap.put("LightPink4", "8B5F65");
		commonColorMap.put("MediumVioletRed", "C71585");
		commonColorMap.put("MistyRose", "FFE4E1");
		commonColorMap.put("MistyRose1", "FFE4E1");
		commonColorMap.put("MistyRose2", "EED5D2");
		commonColorMap.put("MistyRose3", "CDB7B5");
		commonColorMap.put("MistyRose4", "8B7D7B");
		commonColorMap.put("OrangeRed", "FF4500");
		commonColorMap.put("OrangeRed1", "FF4500");
		commonColorMap.put("OrangeRed2", "EE4000");
		commonColorMap.put("OrangeRed3", "CD3700");
		commonColorMap.put("OrangeRed4", "8B2500");
		commonColorMap.put("PaleVioletRed", "DB7093");
		commonColorMap.put("PaleVioletRed1", "FF82AB");
		commonColorMap.put("PaleVioletRed2", "EE799F");
		commonColorMap.put("PaleVioletRed3", "CD6889");
		commonColorMap.put("PaleVioletRed4", "8B475D");
		commonColorMap.put("VioletRed", "D02090");
		commonColorMap.put("VioletRed1", "FF3E96");
		commonColorMap.put("VioletRed2", "EE3A8C");
		commonColorMap.put("VioletRed3", "CD3278");
		commonColorMap.put("VioletRed4", "8B2252");
		commonColorMap.put("firebrick", "B22222");
		commonColorMap.put("firebrick1", "FF3030");
		commonColorMap.put("firebrick2", "EE2C2C");
		commonColorMap.put("firebrick3", "CD2626");
		commonColorMap.put("firebrick4", "8B1A1A");
		commonColorMap.put("pink", "FFC0CB");
		commonColorMap.put("pink1", "FFB5C5");
		commonColorMap.put("pink2", "EEA9B8");
		commonColorMap.put("pink3", "CD919E");
		commonColorMap.put("pink4", "8B636C");
		commonColorMap.put("Flesh", "F5CCB0");
		commonColorMap.put("Feldspar", "D19275");
		commonColorMap.put("red", "FF0000");
		commonColorMap.put("red1", "FF0000");
		commonColorMap.put("red2", "EE0000");
		commonColorMap.put("red3", "CD0000");
		commonColorMap.put("red4", "8B0000");
		commonColorMap.put("tomato", "FF6347");
		commonColorMap.put("tomato1", "FF6347");
		commonColorMap.put("tomato2", "EE5C42");
		commonColorMap.put("tomato3", "CD4F39");
		commonColorMap.put("tomato4", "8B3626");
		commonColorMap.put("Dusty Rose", "856363");
		commonColorMap.put("Firebrick", "8E2323");
		commonColorMap.put("Indian Red", "F5CCB0");
		commonColorMap.put("Pink", "BC8F8F");
		commonColorMap.put("Salmon", "6F4242");
		commonColorMap.put("Scarlet", "8C1717");
		commonColorMap.put("Spicy Pink", "FF1CAE");
		commonColorMap.put("Free Speech Magenta", "E35BD8");
		commonColorMap.put("Free Speech Red", "C00000");
		commonColorMap.put("DarkOrchid", "9932CC");
		commonColorMap.put("DarkOrchid1", "BF3EFF");
		commonColorMap.put("DarkOrchid2", "B23AEE");
		commonColorMap.put("DarkOrchid3", "9A32CD");
		commonColorMap.put("DarkOrchid4", "68228B");
		commonColorMap.put("DarkViolet", "9400D3");
		commonColorMap.put("LavenderBlush", "FFF0F5");
		commonColorMap.put("LavenderBlush1", "FFF0F5");
		commonColorMap.put("LavenderBlush2", "EEE0E5");
		commonColorMap.put("LavenderBlush3", "CDC1C5");
		commonColorMap.put("LavenderBlush4", "8B8386");
		commonColorMap.put("MediumOrchid", "BA55D3");
		commonColorMap.put("MediumOrchid1", "E066FF");
		commonColorMap.put("MediumOrchid2", "D15FEE");
		commonColorMap.put("MediumOrchid3", "B452CD");
		commonColorMap.put("MediumOrchid4", "7A378B");
		commonColorMap.put("MediumPurple", "9370DB");
		commonColorMap.put("Medium Orchid", "9370DB");
		commonColorMap.put("MediumPurple1", "AB82FF");
		commonColorMap.put("Dark Orchid", "9932CD");
		commonColorMap.put("MediumPurple2", "9F79EE");
		commonColorMap.put("MediumPurple3", "8968CD");
		commonColorMap.put("MediumPurple4", "5D478B");
		commonColorMap.put("lavender", "E6E6FA");
		commonColorMap.put("magenta", "FF00FF");
		commonColorMap.put("fuchsia", "FF00FF");
		commonColorMap.put("magenta1", "FF00FF");
		commonColorMap.put("magenta2", "EE00EE");
		commonColorMap.put("magenta3", "CD00CD");
		commonColorMap.put("magenta4", "8B008B");
		commonColorMap.put("maroon", "B03060");
		commonColorMap.put("maroon1", "FF34B3");
		commonColorMap.put("maroon2", "EE30A7");
		commonColorMap.put("maroon3", "CD2990");
		commonColorMap.put("maroon4", "8B1C62");
		commonColorMap.put("orchid", "DA70D6");
		commonColorMap.put("Orchid", "DB70DB");
		commonColorMap.put("orchid1", "FF83FA");
		commonColorMap.put("orchid2", "EE7AE9");
		commonColorMap.put("orchid3", "CD69C9");
		commonColorMap.put("orchid4", "8B4789");
		commonColorMap.put("plum", "DDA0DD");
		commonColorMap.put("plum1", "FFBBFF");
		commonColorMap.put("plum2", "EEAEEE");
		commonColorMap.put("plum3", "CD96CD");
		commonColorMap.put("plum4", "8B668B");
		commonColorMap.put("purple", "A020F0");
		commonColorMap.put("purple", "800080");
		commonColorMap.put("purple1", "9B30FF");
		commonColorMap.put("purple2", "912CEE");
		commonColorMap.put("purple3", "7D26CD");
		commonColorMap.put("purple4", "551A8B");
		commonColorMap.put("thistle", "D8BFD8");
		commonColorMap.put("thistle1", "FFE1FF");
		commonColorMap.put("thistle2", "EED2EE");
		commonColorMap.put("thistle3", "CDB5CD");
		commonColorMap.put("thistle4", "8B7B8B");
		commonColorMap.put("violet", "EE82EE");
		commonColorMap.put("violet blue", "9F5F9F");
		commonColorMap.put("Dark Purple", "871F78");
		commonColorMap.put("Maroon [sic]", "F5CCB0");
		commonColorMap.put("Maroon", "800000");
		commonColorMap.put("Medium Violet Red", "DB7093");
		commonColorMap.put("Neon Pink", "FF6EC7");
		commonColorMap.put("Plum", "EAADEA");
		commonColorMap.put("Thistle", "D8BFD8");
		commonColorMap.put("Turquoise", "ADEAEA");
		commonColorMap.put("Violet", "4F2F4F");
		commonColorMap.put("Violet Red", "CC3299");
		commonColorMap.put("AntiqueWhite", "FAEBD7");
		commonColorMap.put("AntiqueWhite1", "FFEFDB");
		commonColorMap.put("AntiqueWhite2", "EEDFCC");
		commonColorMap.put("AntiqueWhite3", "CDC0B0");
		commonColorMap.put("AntiqueWhite4", "8B8378");
		commonColorMap.put("FloralWhite", "FFFAF0");
		commonColorMap.put("GhostWhite", "F8F8FF");
		commonColorMap.put("NavajoWhite", "FFDEAD");
		commonColorMap.put("NavajoWhite1", "FFDEAD");
		commonColorMap.put("NavajoWhite2", "EECFA1");
		commonColorMap.put("NavajoWhite3", "CDB38B");
		commonColorMap.put("NavajoWhite4", "8B795E");
		commonColorMap.put("OldLace", "FDF5E6");
		commonColorMap.put("WhiteSmoke", "F5F5F5");
		commonColorMap.put("gainsboro", "DCDCDC");
		commonColorMap.put("ivory", "FFFFF0");
		commonColorMap.put("ivory1", "FFFFF0");
		commonColorMap.put("ivory2", "EEEEE0");
		commonColorMap.put("ivory3", "CDCDC1");
		commonColorMap.put("ivory4", "8B8B83");
		commonColorMap.put("linen", "FAF0E6");
		commonColorMap.put("seashell", "FFF5EE");
		commonColorMap.put("seashell1", "FFF5EE");
		commonColorMap.put("seashell2", "EEE5DE");
		commonColorMap.put("seashell3", "CDC5BF");
		commonColorMap.put("seashell4", "8B8682");
		commonColorMap.put("snow", "FFFAFA");
		commonColorMap.put("snow1", "FFFAFA");
		commonColorMap.put("snow2", "EEE9E9");
		commonColorMap.put("snow3", "CDC9C9");
		commonColorMap.put("snow4", "8B8989");
		commonColorMap.put("wheat", "F5DEB3");
		commonColorMap.put("wheat1", "FFE7BA");
		commonColorMap.put("wheat2", "EED8AE");
		commonColorMap.put("wheat3", "CDBA96");
		commonColorMap.put("wheat4", "8B7E66");
		commonColorMap.put("white", "FFFFFF");
		commonColorMap.put("Quartz", "D9D9F3");
		commonColorMap.put("Wheat", "D8D8BF");
		commonColorMap.put("BlanchedAlmond", "FFEBCD");
		commonColorMap.put("DarkGoldenrod", "B8860B");
		commonColorMap.put("DarkGoldenrod1", "FFB90F");
		commonColorMap.put("DarkGoldenrod2", "EEAD0E");
		commonColorMap.put("DarkGoldenrod3", "CD950C");
		commonColorMap.put("DarkGoldenrod4", "8B6508");
		commonColorMap.put("LemonChiffon", "FFFACD");
		commonColorMap.put("LemonChiffon1", "FFFACD");
		commonColorMap.put("LemonChiffon2", "EEE9BF");
		commonColorMap.put("LemonChiffon3", "CDC9A5");
		commonColorMap.put("LemonChiffon4", "8B8970");
		commonColorMap.put("LightGoldenrod", "EEDD82");
		commonColorMap.put("LightGoldenrod1", "FFEC8B");
		commonColorMap.put("LightGoldenrod2", "EEDC82");
		commonColorMap.put("LightGoldenrod3", "CDBE70");
		commonColorMap.put("LightGoldenrod4", "8B814C");
		commonColorMap.put("LightGoldenrodYellow", "FAFAD2");
		commonColorMap.put("LightYellow", "FFFFE0");
		commonColorMap.put("LightYellow1", "FFFFE0");
		commonColorMap.put("LightYellow2", "EEEED1");
		commonColorMap.put("LightYellow3", "CDCDB4");
		commonColorMap.put("LightYellow4", "8B8B7A");
		commonColorMap.put("PaleGoldenrod", "EEE8AA");
		commonColorMap.put("PapayaWhip", "FFEFD5");
		commonColorMap.put("cornsilk", "FFF8DC");
		commonColorMap.put("cornsilk1", "FFF8DC");
		commonColorMap.put("cornsilk2", "EEE8CD");
		commonColorMap.put("cornsilk3", "CDC8B1");
		commonColorMap.put("cornsilk4", "8B8878");
		commonColorMap.put("goldenrod", "DAA520");
		commonColorMap.put("goldenrod1", "FFC125");
		commonColorMap.put("goldenrod2", "EEB422");
		commonColorMap.put("goldenrod3", "CD9B1D");
		commonColorMap.put("goldenrod4", "8B6914");
		commonColorMap.put("moccasin", "FFE4B5");
		commonColorMap.put("yellow", "FFFF00");
		commonColorMap.put("yellow1", "FFFF00");
		commonColorMap.put("yellow2", "EEEE00");
		commonColorMap.put("yellow3", "CDCD00");
		commonColorMap.put("yellow4", "8B8B00");
		commonColorMap.put("gold", "FFD700");
		commonColorMap.put("gold1", "FFD700");
		commonColorMap.put("gold2", "EEC900");
		commonColorMap.put("gold3", "CDAD00");
		commonColorMap.put("gold4", "8B7500");
		commonColorMap.put("Goldenrod", "DBDB70");
		commonColorMap.put("Medium Goldenrod", "EAEAAE");
		commonColorMap.put("Yellow Green", "99CC32");
		commonColorMap.put("copper", "B87333");
		commonColorMap.put("cool copper", "D98719");
		commonColorMap.put("Green Copper", "856363");
		commonColorMap.put("brass", "B5A642");
		commonColorMap.put("bronze", "8C7853");
		commonColorMap.put("bronze II", "A67D3D");
		commonColorMap.put("bright gold", "D9D919");
		commonColorMap.put("Old Gold", "CFB53B");
		commonColorMap.put("CSS Gold", "CC9900");
		commonColorMap.put("gold", "CD7F32");
		commonColorMap.put("silver", "E6E8FA");
		commonColorMap.put("Silver,  Grey", "C0C0C0");
		commonColorMap.put("Light Steel Blue", "545454");
		commonColorMap.put("Steel Blue", "236B8E");
	}

	private ColorVerifingHelper() {
	}

	public static ColorVerifingHelper getInstance() {
		if (instance == null) {
			instance = new ColorVerifingHelper();
		}

		return instance;
	}

//	test case for red
//	public static void main(String[] args) {
//
//		//if you use String.matches , it's case-sensitive , uppercase will be different with lowercase.
//		String fullPattern = "(([rR][gG][bB]\\( *255 *, *0 *, *0 *\\))|(#[fF]{2}0000)|([rR][eE][dD]))";
//		System.out.println("true=>" + "rgb( 255, 0,0)".matches(fullPattern));
//		System.out.println("true=>" + "rgb( 255,   0 , 0 )".matches(fullPattern));
//		System.out.println("false=>" + "rgb( 25, 0,0)".matches(fullPattern));
//		System.out.println("true=>" + "red".matches(fullPattern));
//		System.out.println("false=>" + "redd".matches(fullPattern));
//		System.out.println("true=>" + "rEd".matches(fullPattern));
//
//		System.out.println("--");
//		//here we use (?i) to open sensitive mode. by Tony
//		String insensitivePattern = "(?i)((rgb\\( *255 *, *0 *, *0 *\\))|(#ff0000)|(red))";
//		System.out.println("true=>" + "rgb( 255, 0,0)".matches(insensitivePattern));
//		System.out.println("true=>" + "rgb( 255,   0 , 0 )".matches(insensitivePattern));
//		System.out.println("false=>" + "rgb( 25, 0,0)".matches(insensitivePattern));
//		System.out.println("true=>" + "red".matches(insensitivePattern));
//		System.out.println("false=>" + "redd".matches(insensitivePattern));
//		System.out.println("true=>" + "rEd".matches(insensitivePattern));
//	}

	public boolean isEqualColor(String value1, String value2) {
		if (value1 == null || value2 == null) {
			throw new IllegalArgumentException("Incorrect String! Please check again.");
		}

		String color1 = transform(value1.toLowerCase());
		String color2 = transform(value2.toLowerCase());

		if (color1 == null || color2 == null) {
			return false;
		}

		return color1.equalsIgnoreCase(color2);
	}

	public String transform(String value) {
		if (value.matches(TEMP_HEX)) {
			return value;
		} else if (value.matches(TEMP_RGB)) {
			String temp = value.replace("rgb(", "");
			temp = temp.replace(")", "");
			temp = temp.replace(" ", "");
			String[] colorCodes = temp.split(",");
			StringBuffer strBuff = new StringBuffer("#");

			for(String s : colorCodes) {
				int colorNum = Integer.parseInt(s);
				String hexString = Integer.toHexString(colorNum);
				hexString = hexString.length() > 1 ? hexString : "0" + hexString;
				strBuff.append(hexString);
			}

			return strBuff.toString();
		} else if (value.matches(TEMP_NAMED_COLOR)) {
			return "#" + commonColorMap.get(value);
		}

		throw new IllegalArgumentException("Not a color: \"" + value+"\"");
	}
}
