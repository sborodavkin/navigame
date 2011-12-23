package ua.lw0000.navigame.model;

public class Developer extends Resource {

	public static final int FREE = 0;
	public static final int FEATURE = 1;
	public static final int BUGFIX = 2;
	
	public static final int FEATURE_ANACONDA = 0;
	public static final int FEATURE_NDSLIB = 1;
	public static final int FEATURE_DBTEST = 2;
	
	private static int[] arrNameId = {-1, -1, -1};
	private static int componentId = -1;
	
	private int feature;
	
	public static final String[][] ALL_NAMES = {{
		"EBorshch",
		"IPoletaev",		
		"AKlimenko",
		"DRudetskiy",
		"MMakukha",		
		"IBarykina",
		"MPopov",
		"ANagornaya",
		"NSukhanov",
		"DKovalov",
		"IGulyaev",
		"VUnguryan",
		"YLupol",
		"AKulbatskiy",
		"VBelyaev",
		"VZhuk",
		"SZhuravlev",
		"DPikh",
		"AVasilkov",
		"SGoravskiy",
		"MGorelov",
		"VMalyshev",
		"MVlasyuk",
		"MKrupytsya",
		"ATurbin",
		"AYakovenko",
		"EPivovarov", 
		"YPitomets",
		"MMedvedev",
		"SZaika",
		"VVasyanovich",
		"AKhaysin",
		"IGuk",
		"SPrutskov",
		"DStreltsov",
		"MZaikin",
	}, {
		"ADobrovolskiy",
		"VOsykov",
		"TPolunina",
		"SKuznetsov",
		"DSamokhvalov",
		"AKvartalnyy",
		"ARodichkin",
		"SKulikov",
		"ABagno",
		"PPopov",
		"AReshitko"
	}, {		
		"IGulyanitskiy",
		"DProkopiuk",
		"AKultepin",
		"ARomanyuk",		
		"AKondratiuk",
		"SMikhaylenko"
	}};
	
	public Developer() {
		super();
		setState(FREE);
		init();
		if (componentId == FEATURE_NDSLIB) {
			setSalary(200);
		}
		feature = componentId;
	}
	
	private void init() {
		componentId++;
		if (componentId >= ALL_NAMES.length) {
			componentId = 0;
		}
		arrNameId[componentId]++;
		if (arrNameId[componentId] >= ALL_NAMES[componentId].length) {
			arrNameId[componentId] = 0;
		}
		setTitle(ALL_NAMES[componentId][arrNameId[componentId]]);
	}	
	
	public static void reset() {
		arrNameId[0] = -1;
		arrNameId[1] = -1;
		arrNameId[2] = -1;
		componentId = -1;
		
	}
	
	public int getFeature() {
		return feature;
	}
}
