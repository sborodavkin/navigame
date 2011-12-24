package ua.lw0000.navigame.model;

public class Production extends Resource {
	public static final int FREE = 0;
	public static final int PRODUCTION = 1;
	
	private static int nameId = -1;
	
	public static final String[] ALL_NAMES = {
		"VBrezitskiy",		
		"MPolyakov",
		"IShevchenko",
		"YAndreev",		
		"ARudenko",
		"PMolchinikolov",
		"SDerevyanko",
		"BKalenik",
		"MMospan",
		"TKhodeeva",	
		"PKucheryavyy",
		"VVrublevskiy",
		"SZavodnov",
		"ATurchin",
		"SRatsiborinskiy",
		"YKornienko"
	};
	
	public Production() {
		super();
		setState(FREE);
		initTitle();
	}
	
	private void initTitle() {
		nameId++;
		if (nameId >= ALL_NAMES.length) {
			nameId = 0;
		}
		setTitle(ALL_NAMES[nameId]);
	}
}
