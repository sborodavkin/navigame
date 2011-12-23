package ua.lw0000.navigame.controller;

import java.util.Random;

import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.Production;

public class ShoutGenerator {

	private static String[] EXCEPTIONS = { "AnacondaException",
			"BackingStoreException", "BadLocationException",
			"CertificateException", "ClassNotFoundException",
			"CloneNotSupportedException", "DataFormatException",
			"DestroyFailedException", "ExpandVetoException",
			"FontFormatException", "GeneralSecurityException", "GSSException",
			"IllegalAccessException", "InstantiationException",
			"InterruptedException", "IntrospectionException",
			"InvalidMidiDataException", "InvalidPreferencesFormatException",
			"InvocationTargetException", "IOException", "LastOwnerException",
			"LineUnavailableException", "MidiUnavailableException",
			"MimeTypeParseException", "NamingException",
			"NoninvertibleTransformException", "NoSuchFieldException",
			"NoSuchMethodException", "NotBoundException", "NotOwnerException",
			"ParseException", "ParserConfigurationException",
			"PrinterException", "PrintException", "PrivilegedActionException",
			"PropertyVetoException", "RefreshFailedException",
			"RemarshalException", "RuntimeException", "SAXException",
			"ServerNotActiveException", "SQLException",
			"TooManyListenersException", "TransformerException",
			"UnsupportedAudioFileException", "UnsupportedCallbackException",
			"RejectedExecutionException" };

	private static String[] CLASSES = { "AreaBuilder", "AttributeBuilder",
			"BmdCompilerTarget", "BmdExporter", "BmdFeatureBuilder",
			"BmdLinkAttributeCache", "BmdTileCache", "BmdTileLoader",
			"BmdTileTarget", "BmdUnderConstructionAttributeBuilderTarget",
			"DeathValleyStreetsBuilder", "FeatureBuilder",
			"FeatureSeqNumProvider", "LineBuilder", "NdsParkingComplexTarget",
			"NdsParkingSubtarget", "PointBuilder", "TileBuilder",
			"TilePatternCache", "TilePatternFeatureId",
			"TilePatternReductioner", "TilePatternReductionTarget",
			"TilePatternUtil", "AdjacentRailwaysMerger",
			"AdjacentRailwaysMergerTarget", "RailwaysGeometryChecker",
			"BmdAntiLabelingHintsAttributeCompiler",
			"BmdAttributeCompilerListener", "BmdAttributeMapCompiler",
			"BmdCurvedLabelingHintsAttributeCompiler",
			"BmdCustomAttributeCompilerListener",
			"BmdCustomAttributeMapCompiler",
			"BmdHorizontalLabelingHintsAttributeCompiler",
			"BmdNamedObjectReferenceBuilder", "DrawingOrderAttributeCompiler",
			"ElevationLevelAttributeCompiler", "FerryTypeAttributeCompiler",
			"ParkingAttributeCompiler", "PavementTypeAttributeCompiler",
			"PopulationAttributeCompiler", "RoadZLevelAttributeCompiler",
			"RotatedIconRefAttributeCompiler", "StubbleAttributeCompiler",
			"TmcLocationAttributeCompiler",
			"TouristRouteTypeAttributeCompiler",
			"UnderConstructionAttributeCompiler",
			"BuildingElevationAttributeCompiler",
			"BuildingHeightAttributeCompiler",
			"WallRoofColorAttributesCompiler",
			"NdsCityModel25DColorProcessorTarget",
			"NdsCityModel25DComplexTarget", "NdsCityModel25DSubtarget",
			"DetailedCityMapAreaCalculator", "DetailedCityMapAreaSplitter",
			"DetailedCityMapComplexTarget", "DetailedCityMapLineCalculator",
			"DetailedCityMapLineSplitter", "DetailedCityMapPointCalculator",
			"DetailedCityMapPointSplitter", "BmdIconBuilder",
			"BmdIconSetDataImporter", "RoadNumberClassDataImporter",
			"ThreeDIconsLHComplexTarget", "ThreeDIconsLHSubtarget",
			"BoatFerryIconLHCompiler", "BusIconLHCompiler", "IconLHCompiler",
			"TrainIconLHCompiler", "TunnelIconLHCompiler", "IconType",
			"NeighbourTileHelper", "WorldMapRoadsComplexTarget",
			"WorldMapRoadsSubtarget", /*************** Routing ****************/
			"AdasAttributePointComplexTarget", "AdasAttributePointsTarget",
			"AdasCurvatureComplexTarget", "AdasCurvatureTarget",
			"AdasHeightProfileComplexTarget", "AdasHeightProfileTarget",
			"AdasNodeCurvatureBuilder", "AdasNodeCurvatureComplexTarget",
			"AdasOemStreetTypeBuilderComplexTarget",
			"AdasOemStreetTypeBuilderTarget", "AdasPhysicalWidthComplexTarget",
			"AdasPhysicalWidthTarget", "AdasSlopeComplexTarget",
			"AdasSlopeTarget", "AddressPointComplexTarget",
			"AddressPointTarget", "AttributeCompiler", "BridgeBuilder",
			"CmpcEntranceAndExitTypeTarget",
			"CmpcIntersectionTypeComplexTarget", "CmpcTJunctionTypeTarget",
			"CommonRoutingTileInterface", "DateTimeDefinitionTarget",
			"EcoRoutingImportTarget", "FerryBuilder",
			"GateRestrictionsBuilderTarget", "HouseNumbersRangeComplexTarget",
			"HouseNumbersRangeTarget", "IntersectionBuilder",
			"IntersectionBuilderSubtarget", "IntersectionBuilderTarget",
			"IntersectionCompiler", "LaneBuilderTarget", "LaneCarpoolTarget",
			"LaneConnectivityBuilderTarget", "LaneNumbersBuilderTarget",
			"LaneType", "LaneTypeAndNumberBuilderComplexTarget",
			"LaneTypeAndNumberBuilderTarget", "LinkAttributeUtil",
			"LinkCarpoolTarget", "LinkCompiler",
			"LinkDigitizationStatusComplexTarget",
			"LinkDigitizationStatusTarget", "LinkExpressLaneTarget",
			"LinkTmcLocationBuilder", "LinkTmcLocationBuilderComplexTarget",
			"LinkTmcLocationBuilderSubtarget", "LinkToTileBuilder",
			"LinkTravelDirection", "LinkWarningSignsSubtarget",
			"LinkWarningSignsTarget", "LongRoadIdIterator",
			"MapLineReferenceCompiler", "MapLineReferenceComplexTarget",
			"MapLineReferenceSorter", "MapLineReferenceSubtarget",
			"MergeFixedAttributesComplexTarget",
			"NdsContinuedTurnRestrictionSubtarget",
			"NdsContinuedTurnRestrictionTarget", "NdsTrafficZonesTarget",
			"PhysicalWidthComplexTarget", "PhysicalWidthTarget",
			"PremiumAttributeCompiler", "RoutingAuxTileCompilerInterface",
			"RoutingCompiler", "RoutingCompilerTarget", "RoutingExporter",
			"RoutingTileCache", "RoutingTileCompilerInterface",
			"RoutingTileTarget",
			"RoutingUnderConstructionAttributeBuilderTarget",
			"ServiceLinksTarget", "SignpostComplexTarget", "SignpostTarget",
			"SpecialTransitionBuilder", "SpecialTransitionBuilderSubtarget",
			"SpecialTransitionBuilderTarget", "SpeedLimitsTarget",
			"TileLoader", "TimeRestrictionsTarget", "TimeRestrictionUtil",
			"TollBoothTarget", "TollBuilder", "TollCostBuilder",
			"TrafficLightsTarget", "TrafficPaternSimilarityFilter",
			"TrafficPatternBuilder", "TrafficPatternFilter",
			"TrafficPatternFilterTarget",
			"TrafficPatternGeneralizerComplexTarget",
			"TrafficPatternGeneralizerTarget", "TrafficPatternIdentityFilter",
			"TrafficPatternMseFilter", "TransitionBuilder",
			"TransitionBuilderSubtarget", "TransitionBuilderTarget",
			"TunnelBuilder", "UpDownReferenceBuilder", "UrbanBuilder",
			"VehicleTypesTarget", "WarningSignIconCompiler",
			"AdministrativeAreaNamedObjectReferenceAttributeCompiler",
			"AttributionStatusCompiler", "BridgeAttributeCompiler",
			"ConsumptionSpeedDependencyCompiler",
			"ContinuedTurnRestrictionCompiler",
			"EcoAverageSlopeAttributeCompiler",
			"EcoExcessSlopesAttributeCompiler",
			"EcoSpeedVariationsAttributeCompiler",
			"EnvironmentalZoneAttributeCompiler", "FerryAttributeCompiler",
			"FlexibleLaneAttributeBuilder", "FourWheelDriveCompiler",
			"GateRestrictionAttributeCompiler", "GatewayAttributeCompiler",
			"HouseNumbersAttributeBuilder", "HouseNumbersAttributeCompiler",
			"IntersectionAttributeCompilerListener",
			"IntersectionAttributeMapCompiler",
			"IntersectionPremiumAttributeCompilerListener",
			"IntersectionPremiumAttributeMapCompiler",
			"IntersectionZLevelAttributeCompiler",
			"LaneCarpoolAttributeCompiler",
			"LaneConnectivityAcrossIntersectionAttributeCompiler",
			"LaneDividerAttributeCompiler", "LaneExpressAttributeCompiler",
			"LaneMarkingAttributeCompiler", "LanePassingAttributeCompiler",
			"LanePhysicalDividerTypeCompiler",
			"LaneRestrictionAttributeCompiler",
			"LaneReversibleAttributeCompiler", "LaneTurningAttributeCompiler",
			"LinkAntiLabelingHintsAttributeCompiler",
			"LinkAttributeCompilerListener", "LinkAttributeMapCompiler",
			"LinkCarpoolAttributeCompiler",
			"LinkConcreteNamedObjectReferenceCompiler",
			"LinkDigitizationStatusCompiler",
			"LinkExpressLaneAttributeCompiler", "LinkFerryAttributeCompiler",
			"LinkLabelingHintsAttributeCompiler", "LinkLaneNumberCompiler",
			"LinkNameObjectReferenceAttributeCompiler",
			"LinkPhysicalDividerAttributeCompiler",
			"LinkPostalCodeNameObjectReferenceAttributeCompiler",
			"LinkPremiumAttributeCompilerListener",
			"LinkPremiumAttributeMapCompiler",
			"LinkRotatedIconRefAttributeCompiler", "LinkSpeedLimitsCompiler",
			"LinkTimeRestrictionsCompiler",
			"LinkTrafficPatternAttributeCompiler",
			"LinkTravelDirectionRestrictionsCompiler",
			"LinkTypeAttributeCompiler",
			"LinkUnderConstructionAttributeCompiler",
			"LinkZLevelAttributeCompiler", "MultiDigitizedAttributeCompiler",
			"NamedObjectAndHouseNumberGroupAttributeCompiler",
			"NumTollBoothCompiler", "ParkingAttributeCompiler",
			"PavementTypeAttributeCompiler", "PhysicalWidthAttributeCompiler",
			"RoutingAttributeLayer", "RoutingLinkAttributeCache",
			"SignpostAttributeCompiler", "SpecialTransitionAttributeCompiler",
			"StubbleAttributeCompiler",
			"TileOfContinuedTurnRestrictionCompiler",
			"TimeZoneReferenceCompiler", "TmcLocationAttributeCompiler",
			"TollAttributeCompiler", "TollBoothPaymentTypesCompiler",
			"TollGateAttributeCompiler", "TouristRouteTypeAttributeCompiler",
			"TrafficLightAttributeCompiler",
			"TrafficLightNumberAttributeCompiler",
			"TransitionAttributeCompiler", "TransitionTimeRestrictionCompiler",
			"TunnelAttributeCompiler", "UrbanAttributeCompiller",
			"VehicleTypeDependentRestrictionsAttributeCompiler",
			"VignetteAttributeCompiler", "AdasBaseElevationCompiler",
			"AdasComplienceAttributeCompiler", "AdasElevationDeltaCompiler",
			"AttributePointListCompiler", "CmpcIntersectionTypeCompiler",
			"CurvatureCompiler", "OemStreetTypeCompiler",
			"PhysicalWidthImperialCompiler", "PhysicalWidthMetricCompiler",
			"SlopeAttributeCompiler", "TurnGeometryCurvatureAttribute",
			"TurnGeometryCurvatureCompiler", "WarningSignAttributeCompiler",
			"DateTimeAttributesBuilder", "LaneMasksAttributeBuilder",
			"VehicleTypesAttributeBuilder", "LinkGroupNumberGenerator",
			"TransitionGroupNumberGenerator", "AverageSlopeCalculator",
			"CurvaturePenaltyCalculator", "EcoCurvaturePenaltyBuilder",
			"EcoSpeedMappingTarget", "EcoSpeedVariationsComplexTarget",
			"EcoSpeedVariationsTarget", "EcoTransitionPenaltyBuilder",
			"ExcessSlopeCalculator", "ExcessSlopesComplexTarget",
			"ExcessSlopesTarget", "SpeedLengthTarget",
			"GatewayBuilderComplexTarget", "GatewayBuilderSubtarget",
			"MarkGatewayLinkTarget", "ReferenceBuilder" };

	private static String[] VERSIONS = { "2.1", "2.2", "2.3", "2.4", "2.5", "2.6",
			"3.0", "3.1", "3.2", "3.3", "3.4" };

	private static String[] CURSES_PREFIX = {
		"Fucking", "Bloody", "Holy", "Stupid", "Damn", "Crappy"
	};
	
	private static String[] CURSES = {
		"Fuck", "Dick", "Asshole", "Dickhead", "WTF", "Shit", "Muppet", "Bollock",
		"Hell", "Cock", "Doughnut", "Twat", "Prat", "Tosser", "Knobhead", "Bitch",
		"Shithead", "Wanker", "Moron"
	};
	
	private static String[] CURSES_ON_JAVA = {
		"Eclipse", "IDEA", "code", "OSGi", "Hibernate", "Java", "NDS", "Datascript",
		"Attribute", "Perforce", "JIRA", "ticket", "MANAGEMENT", "Lane", "Link",
		"Intersection", "Tile", "Junction", "AMD", "BMD", "Routing", "Naming",
		"BLOB", "WORLD MAP", "Transition", "Polygon", "Feature", "Geometry", "SDDD"
	};

	private static String[] CURSES_ON_CPP = {
		"Studio", "STL", "template", "code review", "design", "C++", "NDS", "Datascript",
		"Attribute", "DLL", "Linker", "LUXproject", "Perforce", "Lane", "Link",
		"Intersection", "Tile", "Junction", "AMD", "BMD", "Routing", "Naming",
		"BLOB", "WORLD MAP", "Transition", "Polygon", "Feature", "Geometry", "SDDD"
	};
	
	private static String[] CURSES_ON_PRODUCTION = {
		"Raw data", "Compiler", "Anaconda", "BMD", "Routing", "Naming", "POI", "AMD",
		"Map viewer", "Report", "ticket", "JIRA", "server", "worker", "Postgres", "SQLite",
		"config", "XML", "Putty", "WinSCP"
	};
	
	
	public static String generateDeveloperCurse(Developer dev) {
		String res = "";
		Random rand = new Random(System.currentTimeMillis());
		if (rand.nextFloat() > 0.5) {
			res = "What a ";
		}
		if (rand.nextFloat() > 0.5) {
			res += CURSES_PREFIX[rand.nextInt(CURSES_PREFIX.length)] + " " + CURSES[rand.nextInt(CURSES.length)];
		} else {
			res += CURSES[rand.nextInt(CURSES.length)];
		}
		if (rand.nextFloat() > 0.5) {
			if (dev.getFeature() == Developer.FEATURE_NDSLIB) {
				res += " " + CURSES_ON_CPP[rand.nextInt(CURSES_ON_CPP.length)];
			} else {
				res += " " + CURSES_ON_JAVA[rand.nextInt(CURSES_ON_JAVA.length)];
			}
		}
		return res.toUpperCase() + "!";
	}
	
	public static String generateProductionCurse(Production prod) {
		String res = "";
		Random rand = new Random(System.currentTimeMillis());
		if (rand.nextFloat() > 0.5) {
			res = "What a ";
		}
		if (rand.nextFloat() > 0.5) {
			res += CURSES_PREFIX[rand.nextInt(CURSES_PREFIX.length)] + " " + CURSES[rand.nextInt(CURSES.length)];
		} else {
			res += CURSES[rand.nextInt(CURSES.length)];
		}
		if (rand.nextFloat() > 0.5) {			
			res += " " + CURSES_ON_PRODUCTION[rand.nextInt(CURSES_ON_PRODUCTION.length)];			
		}
		return res.toUpperCase() + "!";
	}
	
	public static String generateBug(String mapTitle) {
		String res = "";
		Random rand = new Random(System.currentTimeMillis());
		res = generateId(rand) + " ";
		res += generateException(rand) + " at ";
		res += generateClassName(rand);
		res += " - Anaconda " + generateVersion(rand);
		return res;
	}	

	private static String generateId(Random rand) {
		return "NAV2010ANA-" + (rand.nextInt(3000) + 1500);
	}

	private static String generateException(Random rand) {
		return EXCEPTIONS[rand.nextInt(EXCEPTIONS.length)];
	}

	private static String generateClassName(Random rand) {
		return CLASSES[rand.nextInt(CLASSES.length)];
	}

	private static String generateVersion(Random rand) {
		return VERSIONS[rand.nextInt(VERSIONS.length)];
	}
}
