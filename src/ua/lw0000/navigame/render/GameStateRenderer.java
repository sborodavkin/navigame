package ua.lw0000.navigame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ua.lw0000.navigame.controller.HWController;
import ua.lw0000.navigame.controller.MoneyController;
import ua.lw0000.navigame.controller.MoodController;
import ua.lw0000.navigame.controller.TimeController;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.main.NaviGame;

public class GameStateRenderer implements Renderer {

	private MoneyController moneyController;
	private TimeController timeController;
	private MoodController moodController;
	private HWController hwController;
	private Context context;
	private AnchorMouseOverArea btnRequestOvertimes;

	private AnchorMouseOverArea btnHWBasic;
	private AnchorMouseOverArea btnHWAdvanced;
	private AnchorMouseOverArea btnHWSuper;;

	public GameStateRenderer(MoneyController moneyCtrl,
			TimeController timeController, MoodController moodController,
			HWController hwCtrl, Context ctx) {
		this.moneyController = moneyCtrl;
		this.timeController = timeController;
		this.moodController = moodController;
		this.hwController = hwCtrl;
		this.context = ctx;

		btnRequestOvertimes = new AnchorMouseOverArea(NaviGame.gameContainer,
				context.getOvertimeImg(), 0, 90, 100, 30) {
			@Override
			public void mousePressed(int button, int mx, int my) {
				super.mousePressed(button, mx, my);
				if (isMouseOver()) {
					moneyController.setOvertime(true);
				}
			}
		};
		btnRequestOvertimes.setAnchorW(Composition.LEFT_PANEL_W);
		btnRequestOvertimes.setAnchorN(Composition.ROOM_NW);
		btnRequestOvertimes.setMouseOverImage(context.getOvertimeHoverImg());
		btnRequestOvertimes.setMouseDownImage(context.getOvertimeImg());

		btnHWBasic = new AnchorMouseOverArea(NaviGame.gameContainer,
				context.getHwBasicDisabledImg(), 0, 175, 40, 40) {
			@Override
			public void mousePressed(int button, int mx, int my) {
				super.mousePressed(button, mx, my);
				if (isMouseOver()
						&& hwController.getHW() != HWController.HW_BASIC) {
					hwController.upgradeHW(HWController.HW_BASIC);
				}
			}
		};
		btnHWBasic.setAnchorW(Composition.LEFT_PANEL_W);
		btnHWBasic.setAnchorN(Composition.ROOM_NW);
		btnHWAdvanced = new AnchorMouseOverArea(NaviGame.gameContainer,
				context.getHwAdvancedDisabledImg(), 50, 175, 40, 40) {
			@Override
			public void mousePressed(int button, int mx, int my) {
				super.mousePressed(button, mx, my);
				if (isMouseOver()
						&& hwController.getHW() != HWController.HW_ADVANCED) {
					hwController.upgradeHW(HWController.HW_ADVANCED);
				}
			}
		};
		btnHWAdvanced.setAnchorW(Composition.LEFT_PANEL_W);
		btnHWAdvanced.setAnchorN(Composition.ROOM_NW);
		btnHWSuper = new AnchorMouseOverArea(NaviGame.gameContainer,
				context.getHwSuperDisabledImg(), 100, 175, 40, 40) {
			@Override
			public void mousePressed(int button, int mx, int my) {
				super.mousePressed(button, mx, my);
				if (isMouseOver()
						&& hwController.getHW() != HWController.HW_SUPER) {
					hwController.upgradeHW(HWController.HW_SUPER);
				}
			}
		};
		btnHWSuper.setAnchorW(Composition.LEFT_PANEL_W);
		btnHWSuper.setAnchorN(Composition.ROOM_NW);
	}

	@Override
	public Image render() throws SlickException {

		Graphics imgGraphics = context.getGenImageGraphics();
		imgGraphics.clear();
		renderSalary(imgGraphics, 0, 0);
		renderHardware(imgGraphics, 0, 130);
		renderCompiler(imgGraphics, 0, 230);
		renderTeamMood(imgGraphics, 0, 280);

		imgGraphics.flush();
		return context.getGenImage().copy();
	}

	private void renderSalary(Graphics imgGraphics, float x, float y) {
		imgGraphics.setFont(context.getBigFont());
		imgGraphics.drawString("$" + moneyController.getGameState().getMoney(),
				x, y);

		imgGraphics.setFont(context.getMediumFont());
		Color prevColor = imgGraphics.getColor();
		if (moneyController.isOvertime()) {
			imgGraphics.setColor(new Color(255, 14, 69, 255));
		}
		imgGraphics.drawString(
				"Next Salary: $" + moneyController.getTotalSalary(), x, y + 40);
		imgGraphics.setColor(prevColor);

		int timeFromLastSalary = moneyController.getGameState().getTime()
				- timeController.getTimeSinceLastSalary();

		float salaryProgress = (float) (timeFromLastSalary
				* (double) Composition.LEFT_PANEL_WIDTH / (double) TimeController.SALARY_INTERVAL);
	
		float x2 = salaryProgress; //(float) (salaryProgress * Composition.LEFT_PANEL_WIDTH);
		context.getProgress150ProgressImg().draw(x + 1, y + 70, x2,
				y + 70 + context.getProgress150ProgressImg().getHeight(), 0, 0,
				x2, context.getProgress150ProgressImg().getHeight());
		context.getProgress150FrameImg().draw(x + 1, y + 70);
		

		if (!moneyController.isOvertime()) {
			btnRequestOvertimes.setNormalImage(context.getOvertimeImg());
			btnRequestOvertimes
					.setMouseOverImage(context.getOvertimeHoverImg());
			btnRequestOvertimes.setMouseDownImage(context.getOvertimeImg());
		} else {
			btnRequestOvertimes.setNormalImage(context.getOvertimeLockedImg());
			btnRequestOvertimes.setMouseOverImage(context
					.getOvertimeLockedImg());
			btnRequestOvertimes.setMouseDownImage(context
					.getOvertimeLockedImg());
		}

		btnRequestOvertimes.render(NaviGame.gameContainer, imgGraphics);

	}

	private void renderHardware(Graphics imgGraphics, float x, float y) {
		imgGraphics.setFont(context.getMediumFont());
		Color prevColor = imgGraphics.getColor();
		imgGraphics.setColor(Color.white);
		imgGraphics.drawString("Hardware upgrade:", x, y);
		// imgGraphics.setFont(context.getPriceFont());
		if (hwController.isEnabled(HWController.HW_ADVANCED)) {
			btnHWAdvanced.setNormalImage(context.getHwAdvancedImg());
			if (hwController.getHW() != HWController.HW_ADVANCED) {
				btnHWAdvanced
						.setMouseOverImage(context.getHwAdvancedHoverImg());
			} else {
				btnHWAdvanced.setMouseOverImage(context.getHwAdvancedImg());
			}
			btnHWAdvanced.setAcceptingInput(true);
		} else {
			btnHWAdvanced.setNormalImage(context.getHwAdvancedDisabledImg());
			btnHWAdvanced.setMouseOverImage(context.getHwAdvancedDisabledImg());
			btnHWAdvanced.setAcceptingInput(false);
		}
		if (hwController.isEnabled(HWController.HW_SUPER)) {
			btnHWSuper.setNormalImage(context.getHwSuperImg());
			if (hwController.getHW() != HWController.HW_SUPER) {
				btnHWSuper.setMouseOverImage(context.getHwSuperHoverImg());
			} else {
				btnHWSuper.setMouseOverImage(context.getHwSuperImg());
			}
			btnHWSuper.setAcceptingInput(true);
		} else {
			btnHWSuper.setNormalImage(context.getHwSuperDisabledImg());
			btnHWSuper.setMouseOverImage(context.getHwSuperDisabledImg());
			btnHWSuper.setAcceptingInput(false);
		}
		if (hwController.isEnabled(HWController.HW_BASIC)) {
			btnHWBasic.setNormalImage(context.getHwBasicImg());
			if (hwController.getHW() != HWController.HW_BASIC) {
				btnHWBasic.setMouseOverImage(context.getHwBasicHoverImg());
			} else {
				btnHWBasic.setMouseOverImage(context.getHwBasicImg());
			}
			btnHWBasic.setAcceptingInput(true);
		} else {
			btnHWBasic.setNormalImage(context.getHwBasicDisabledImg());
			btnHWBasic.setMouseOverImage(context.getHwBasicDisabledImg());
			btnHWBasic.setAcceptingInput(false);
		}

		btnHWBasic.render(NaviGame.gameContainer, imgGraphics);
		btnHWAdvanced.render(NaviGame.gameContainer, imgGraphics);
		btnHWSuper.render(NaviGame.gameContainer, imgGraphics);
		imgGraphics
				.drawString("$" + HWController.PAYMENT_BASIC, x + 10, y + 25);
		imgGraphics.drawString("$" + HWController.PAYMENT_ADVANCED,
				x + 10 + 50, y + 25);
		imgGraphics.drawString("$" + HWController.PAYMENT_SUPER, x + 10 + 100,
				y + 25);

		float xChk = x;
		if (hwController.getHW() == HWController.HW_ADVANCED) {
			xChk = x + 60;
		} else if (hwController.getHW() == HWController.HW_SUPER) {
			xChk = x + 110;
		} else {
			xChk = x;
		}
		imgGraphics.drawImage(context.getCheckmark(), xChk, y + 70);

		imgGraphics.setColor(prevColor);
	}

	private void renderCompiler(Graphics imgGraphics, float x, float y) {
		imgGraphics.setFont(context.getMediumFont());
		imgGraphics.drawString("Navi-Progress:", x, y);

		double compiler = moneyController.getGameState().getCompilerComplete();

		float x2 = (float) (compiler * Composition.LEFT_PANEL_WIDTH / 100f);
		context.getProgress150ProgressImg().draw(x + 1, y + 30, x2,
				y + 30 + context.getProgress150ProgressImg().getHeight(), 0, 0,
				x2, context.getProgress150ProgressImg().getHeight());
		context.getProgress150FrameImg().draw(x + 1, y + 30);
	}

	private void renderTeamMood(Graphics imgGraphics, float x, float y) {
		int devMood = moodController.getDevelopersPerformance();
		int prodMood = moodController.getProductionPerformance();
		renderMood(imgGraphics, "Developers Mood:", devMood, x, y);
		renderMood(imgGraphics, "Production Mood:", prodMood, x, y + 50);
	}

	private void renderMood(Graphics imgGraphics, String title, int mood,
			float x, float y) {
		imgGraphics.setFont(context.getMediumFont());
		imgGraphics.drawString(title, x, y);

		if ((float) mood / 100 >= DeveloperRenderer.MOOD_OK_BORDER) {
			context.getMoodOkImg().draw(x + 1 + 8, y + 24);
		} else if ((float) mood / 100 >= DeveloperRenderer.MOOD_AVG_BORDER) {
			context.getMoodAvgImg().draw(x + 1, y + 24);
		} else {
			context.getMoodBadImg().draw(x + 1, y + 24);
		}

		float x2 = (float)mood * (Composition.LEFT_PANEL_WIDTH - 32) / 100f;
		context.getProgress118ProgressImg().draw(x + 32 + 1, y + 30, x2 + 32,
				y + 30 + context.getProgress150ProgressImg().getHeight(), 0, 0,
				x2, context.getProgress150ProgressImg().getHeight());
		context.getProgress118FrameImg().draw(x + 32 + 1, y + 30);
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		// noop
	}
}
