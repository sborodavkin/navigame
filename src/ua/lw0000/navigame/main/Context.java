package ua.lw0000.navigame.main;

import static ua.lw0000.navigame.main.Composition.MAP_HEIGHT;
import static ua.lw0000.navigame.main.Composition.MAP_WIDTH;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Context {
	
	private static final String PATH_BACKGROUND = "image/global_background.png";
	private static final String PATH_DEVELOPER_COMPILER = "image/developer_compiler.png";
	private static final String PATH_DEVELOPER_DBTESTS = "image/developer_dbtests.png";
	private static final String PATH_DEVELOPER_NDSLIB = "image/developer.png";
	private static final String PATH_PRODUCTION = "image/production.png";
	private static final String PATH_EMPTY = "image/empty.png";
	private static final String PATH_CURSE_FONT = "image/font_curse.fnt";
	private static final String PATH_CURSE_FONT_IMAGE = "image/font_curse_0.tga";
	private static final String PATH_SMALL_FONT = "image/font_small.fnt";
	private static final String PATH_SMALL_FONT_IMAGE = "image/font_small_0.tga";
	private static final String PATH_SMALL_BLACK_FONT = "image/font_small_black.fnt";
	private static final String PATH_SMALL_BLACK_FONT_IMAGE = "image/font_small_black_0.tga";
	private static final String PATH_MEDIUM_FONT = "image/font_medium.fnt";
	private static final String PATH_MEDIUM_FONT_IMAGE = "image/font_medium_0.tga";
	private static final String PATH_BIG_FONT = "image/font_big.fnt";
	private static final String PATH_BIG_FONT_IMAGE = "image/font_big_0.tga";
	private static final String PATH_BIG_WHITE_FONT = "image/font_big_white.fnt";
	private static final String PATH_BIG_WHITE_FONT_IMAGE = "image/font_big_white_0.tga";
	private static final String PATH_BIG_WHITE_LIGHT_FONT = "image/font_big_white_light.fnt";
	private static final String PATH_BIG_WHITE_LIGHT_FONT_IMAGE = "image/font_big_white_light_0.tga";
	private static final String PATH_PRICE_FONT = "image/font_price.fnt";
	private static final String PATH_PRICE_FONT_IMAGE = "image/font_price_0.tga";
	private static final String PATH_ALL_MAPS = "image/maps.png";
	private static final String PATH_BUG = "image/bug.png";
	private static final String PATH_FIX = "image/fix.png";
	private static final String PATH_BTN_SALARY = "image/dollar.png";
	private static final String PATH_BTN_MOTIVATION = "image/lamp.png";
	private static final String PATH_BTN_SALARY_PRESSED = "image/dollar_pressed.png";
	private static final String PATH_BTN_MOTIVATION_PRESSED = "image/lamp_pressed.png";
	private static final String PATH_BTN_SALARY_HOVER = "image/dollar_hover.png";
	private static final String PATH_BTN_MOTIVATION_HOVER = "image/lamp_hover.png";
	private static final String PATH_SHOUT_CLOUD = "image/shout_cloud.png";
	
	private static final String PATH_MOOD_OK = "image/sun.png";
	private static final String PATH_MOOD_AVG = "image/sun_cloud.png";
	private static final String PATH_MOOD_BAD = "image/cloud.png";
	
	private static final String PATH_OVERTIME = "image/overtime.png";
	private static final String PATH_OVERTIME_HOVER = "image/overtime_hover.png";
	private static final String PATH_OVERTIME_LOCKED = "image/overtime_locked.png";
	
	private static final String PATH_PKUNATH = "image/pkunath.png";
	private static final String PATH_CALLOUT = "image/callout.png";
	
	private static final String PATH_HW_BASIC = "image/hw_basic.png";
	private static final String PATH_HW_BASIC_DISABLED = "image/hw_basic_disabled.png";
	private static final String PATH_HW_BASIC_HOVER = "image/hw_basic_hover.png";
	private static final String PATH_HW_ADVANCED = "image/hw_advanced.png";
	private static final String PATH_HW_ADVANCED_HOVER = "image/hw_advanced_hover.png";
	private static final String PATH_HW_ADVANCED_DISABLED = "image/hw_advanced_disabled.png";
	private static final String PATH_HW_SUPER = "image/hw_super.png";
	private static final String PATH_HW_SUPER_HOVER = "image/hw_super_hover.png";
	private static final String PATH_HW_SUPER_DISABLED = "image/hw_super_disabled.png";
	private static final String PATH_CHECKMARK = "image/checkmark.png";
	
	private static final String PATH_PROGRESS_150_FRAME = "image/progress_150_frame.png";
	private static final String PATH_PROGRESS_150_PROGRESS = "image/progress_150_progress.png";
	private static final String PATH_PROGRESS_118_FRAME = "image/progress_118_frame.png";
	private static final String PATH_PROGRESS_118_PROGRESS = "image/progress_118_progress.png";
	
	/**
	 * Is used to draw procedurally-generated images
	 * (e.g. sprite + text etc.)
	 */
	private Image generatedPosition;
	private Graphics generatedPositionGraphics;
	private Font fontSmall;
	private Font fontSmallBlack;
	private Font fontMedium;
	private Font fontBig;
	private Font fontCurse;
	private Font fontPrice;
	private Font fontBigWhite;
	private Font fontBigWhiteLight;
	
	
	private Image background;
	private Image emptyPosition;
	private Image developerCompilerPosition;
	private Image developerNDSLibPosition;
	private Image developerDBTestsPosition;
	private Image productionPosition;
	private Image bugImg;
	private Image fixImg;
	private Image btnSalaryImg;
	private Image btnMotivationImg;
	private Image btnSalaryPressedImg;
	private Image btnMotivationPressedImg;
	private Image btnSalaryHoverImg;
	private Image btnMotivationHoverImg;
	private Image moodOkImg;
	private Image moodAvgImg;
	private Image moodBadImg;
	private Image shoutCloudImg;
	private Image overtimeImg;
	private Image overtimeHoverImg;
	private Image overtimeLockedImg;
	private Image pKunathImg;
	private Image calloutImg;
	
	private Image hwBasicImg;
	private Image hwBasicHoverImg;
	private Image hwBasicDisabledImg;
	private Image hwAdvancedImg;
	private Image hwAdvancedHoverImg;
	private Image hwAdvancedDisabledImg;
	private Image hwSuperImg;
	private Image hwSuperHoverImg;
	private Image hwSuperDisabledImg;
	private Image checkmark;
	private Image progress150FrameImg;
	private Image progress150ProgressImg;
	private Image progress118FrameImg;
	private Image progress118ProgressImg;
	
	private Image[] mapImg;
		
	
	
	public Context() throws SlickException {	
		mapImg = new Image[6]; 
		initGraphics();
		initFonts();
	}
	
	private void initGraphics() throws SlickException {
		background = new Image(PATH_BACKGROUND);
		emptyPosition = new Image(PATH_EMPTY);
		developerDBTestsPosition = new Image(PATH_DEVELOPER_DBTESTS);
		developerCompilerPosition = new Image(PATH_DEVELOPER_COMPILER);
		developerNDSLibPosition = new Image(PATH_DEVELOPER_NDSLIB);
		productionPosition = new Image(PATH_PRODUCTION);	
		generatedPosition = new Image(Composition.WIDTH,Composition.HEIGHT);
		
		Image allMaps = new Image(PATH_ALL_MAPS);
		mapImg[0] = allMaps.getSubImage(0, 0, MAP_WIDTH, MAP_HEIGHT);
		mapImg[1] = allMaps.getSubImage(0, 1*MAP_HEIGHT, MAP_WIDTH, MAP_HEIGHT);
		mapImg[2] = allMaps.getSubImage(0, 2*MAP_HEIGHT, MAP_WIDTH, MAP_HEIGHT);
		mapImg[3] = allMaps.getSubImage(0, 3*MAP_HEIGHT, MAP_WIDTH, MAP_HEIGHT);
		mapImg[4] = allMaps.getSubImage(0, 4*MAP_HEIGHT, MAP_WIDTH, MAP_HEIGHT);
		mapImg[5] = allMaps.getSubImage(0, 5*MAP_HEIGHT, MAP_WIDTH, MAP_HEIGHT);
		
		generatedPositionGraphics = generatedPosition.getGraphics();
		
		bugImg = new Image(PATH_BUG);
		fixImg = new Image(PATH_FIX);
		btnSalaryImg = new Image(PATH_BTN_SALARY);
		btnMotivationImg = new Image(PATH_BTN_MOTIVATION);
		btnSalaryPressedImg = new Image(PATH_BTN_SALARY_PRESSED);
		btnMotivationPressedImg = new Image(PATH_BTN_MOTIVATION_PRESSED);
		btnSalaryHoverImg = new Image(PATH_BTN_SALARY_HOVER);
		btnMotivationHoverImg = new Image(PATH_BTN_MOTIVATION_HOVER);
		moodOkImg = new Image(PATH_MOOD_OK);
		moodAvgImg = new Image(PATH_MOOD_AVG);
		moodBadImg = new Image(PATH_MOOD_BAD);
		shoutCloudImg = new Image(PATH_SHOUT_CLOUD);
		overtimeImg = new Image(PATH_OVERTIME);
		overtimeHoverImg = new Image(PATH_OVERTIME_HOVER);
		overtimeLockedImg = new Image(PATH_OVERTIME_LOCKED);
		pKunathImg = new Image(PATH_PKUNATH);
		calloutImg = new Image(PATH_CALLOUT);
		
		hwBasicImg = new Image(PATH_HW_BASIC);
		hwBasicHoverImg = new Image(PATH_HW_BASIC_HOVER);
		hwBasicDisabledImg = new Image(PATH_HW_BASIC_DISABLED);
		hwAdvancedImg = new Image(PATH_HW_ADVANCED);
		hwAdvancedHoverImg = new Image(PATH_HW_ADVANCED_HOVER);
		hwAdvancedDisabledImg = new Image(PATH_HW_ADVANCED_DISABLED);
		hwSuperImg = new Image(PATH_HW_SUPER);
		hwSuperHoverImg = new Image(PATH_HW_SUPER_HOVER);
		hwSuperDisabledImg = new Image(PATH_HW_SUPER_DISABLED);
		checkmark = new Image(PATH_CHECKMARK);
		
		progress150FrameImg = new Image(PATH_PROGRESS_150_FRAME);
		progress150ProgressImg = new Image(PATH_PROGRESS_150_PROGRESS);
		progress118FrameImg = new Image(PATH_PROGRESS_118_FRAME);
		progress118ProgressImg = new Image(PATH_PROGRESS_118_PROGRESS);
	}
	
	private void initFonts() throws SlickException {
		Image imgSmallFont = new Image(PATH_SMALL_FONT_IMAGE);
		fontSmall = new AngelCodeFont(PATH_SMALL_FONT, imgSmallFont);
		
		Image imgSmallBlackFont = new Image(PATH_SMALL_BLACK_FONT_IMAGE);
		fontSmallBlack = new AngelCodeFont(PATH_SMALL_BLACK_FONT, imgSmallBlackFont);
		
		Image imgMediumFont = new Image(PATH_MEDIUM_FONT_IMAGE);
		fontMedium = new AngelCodeFont(PATH_MEDIUM_FONT, imgMediumFont);
		
		Image imgBigFont = new Image(PATH_BIG_FONT_IMAGE);
		fontBig = new AngelCodeFont(PATH_BIG_FONT, imgBigFont);
		
		Image imgBigWhiteFont = new Image(PATH_BIG_WHITE_FONT_IMAGE);
		fontBigWhite = new AngelCodeFont(PATH_BIG_WHITE_FONT, imgBigWhiteFont);
		
		Image imgBigWhiteLightFont = new Image(PATH_BIG_WHITE_LIGHT_FONT_IMAGE);
		fontBigWhiteLight = new AngelCodeFont(PATH_BIG_WHITE_LIGHT_FONT, imgBigWhiteLightFont);
		
		Image imgCurseFont = new Image(PATH_CURSE_FONT_IMAGE);
		fontCurse = new AngelCodeFont(PATH_CURSE_FONT, imgCurseFont);
		
		Image imgPriceFont = new Image(PATH_PRICE_FONT_IMAGE);
		fontPrice = new AngelCodeFont(PATH_PRICE_FONT, imgPriceFont);
	}
	
	public Graphics getGenImageGraphics() {
		return generatedPositionGraphics;
	}
	
	public Image getGenImage() {
		return generatedPosition;
	}
	
	public Image getBackground() {
		return background;
	}	
	
	public Image getEmpty() {
		return emptyPosition;
	}
	
	public Image getDeveloperCompilerImage() {
		return developerCompilerPosition;
	}
	
	public Image getDeveloperNDSLibImage() {
		return developerNDSLibPosition;
	}
	
	public Image getDeveloperDBTestsImage() {
		return developerDBTestsPosition;
	}
		
	public Image getProduction() {
		return productionPosition;
	}
	
	public Font getSmallFont() {
		return fontSmall;
	}
	
	public Font getSmallBlackFont() {
		return fontSmallBlack;
	}
	
	public Font getBigWhiteFont() {
		return fontBigWhite;
	}
	
	public Font getBigWhiteLightFont() {
		return fontBigWhiteLight;
	}

	public Font getMediumFont() {
		return fontMedium;
	}
	
	public Font getBigFont() {
		return fontBig;
	}
	
	public Font getCurseFont() {
		return fontCurse;
	}
	
	public Font getPriceFont() {
		return fontPrice;
	}
	
	public Image getMapImage(int index) {
		return mapImg[index];
	}
	
	public Image getBugImage() {
		return bugImg;
	}
	
	public Image getFixImage() {
		return fixImg;
	}

	public Image getBtnSalaryImg() {
		return btnSalaryImg;
	}

	public Image getBtnMotivationImg() {
		return btnMotivationImg;
	}

	public Image getBtnSalaryPressedImg() {
		return btnSalaryPressedImg;
	}

	public Image getBtnMotivationPressedImg() {
		return btnMotivationPressedImg;
	}
	
	public Image getBtnSalaryHoverImg() {
		return btnSalaryHoverImg;
	}

	public Image getBtnMotivationHoverImg() {
		return btnMotivationHoverImg;
	}

	public Image getMoodOkImg() {
		return moodOkImg;
	}

	public Image getMoodAvgImg() {
		return moodAvgImg;
	}

	public Image getMoodBadImg() {
		return moodBadImg;
	}
	
	public Image getShoutCloudImg() {
		return shoutCloudImg;
	}
	
	public Image getOvertimeImg() {
		return overtimeImg;
	}
	public Image getOvertimeHoverImg() {
		return overtimeHoverImg;
	}
	public Image getOvertimeLockedImg() {
		return overtimeLockedImg;
	}

	public Image getpKunathImg() {
		return pKunathImg;
	}

	public Image getCalloutImg() {
		return calloutImg;
	}

	public Image getHwBasicImg() {
		return hwBasicImg;
	}

	public Image getHwBasicDisabledImg() {
		return hwBasicDisabledImg;
	}

	public Image getHwAdvancedImg() {
		return hwAdvancedImg;
	}

	public Image getHwAdvancedDisabledImg() {
		return hwAdvancedDisabledImg;
	}

	public Image getHwSuperImg() {
		return hwSuperImg;
	}

	public Image getHwSuperDisabledImg() {
		return hwSuperDisabledImg;
	}
	
	public Image getHwBasicHoverImg() {
		return hwBasicHoverImg;
	}

	public Image getHwAdvancedHoverImg() {
		return hwAdvancedHoverImg;
	}

	public Image getHwSuperHoverImg() {
		return hwSuperHoverImg;
	}

	public Image getCheckmark() {
		return checkmark;
	}

	public Image getProgress150FrameImg() {
		return progress150FrameImg;
	}

	public Image getProgress150ProgressImg() {
		return progress150ProgressImg;
	}

	public Image getProgress118FrameImg() {
		return progress118FrameImg;
	}

	public Image getProgress118ProgressImg() {
		return progress118ProgressImg;
	}
	
	
}

