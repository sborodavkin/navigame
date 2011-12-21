package ua.lw0000.navigame.controller;

/**
 * The controller handling available HW and upgrades
 * 
 * @author SBorodavkin
 * 
 */
public class HWController {

	public static final int HW_BASIC = 0;
	public static final int HW_ADVANCED = 1;
	public static final int HW_SUPER = 2;

	public static final int PAYMENT_BASIC = 500;
	public static final int PAYMENT_ADVANCED = 2500;
	public static final int PAYMENT_SUPER = 7000;

	private MoneyController moneyController;
	private float hwMultiplier;

	/**
	 * The constructor.
	 * 
	 * @param moneyCtrl
	 */
	public HWController(MoneyController moneyCtrl) {
		moneyController = moneyCtrl;
		hwMultiplier = 1;
	}

	/**
	 * Gets the amount of money needed to upgrade to a given HW type
	 * 
	 * @param hwType
	 *            type of HW (HW_BASIC, HW_ADVANCED, HW_SUPER)
	 * @return payment amount
	 */
	public static final int getPaymentForHWType(int hwType) {
		switch (hwType) {
		case HW_BASIC:
			return PAYMENT_BASIC;
		case HW_ADVANCED:
			return PAYMENT_ADVANCED;
		case HW_SUPER:
			return PAYMENT_SUPER;
		}
		return 0;
	}

	/**
	 * Gets the multiplication factor of a given HW type. All activities like
	 * compiler feature increment speed, production speed and bugfix speed are
	 * multiplied by it.
	 * 
	 * @param hwType
	 *            type of HW (HW_BASIC, HW_ADVANCED, HW_SUPER)
	 * @return multiplication factor
	 */
	public static final float getHWMultiplierForHWType(int hwType) {
		switch (hwType) {
		case HW_BASIC:
			return 1.0f;
		case HW_ADVANCED:
			return 2.0f;
		case HW_SUPER:
			return 3.0f;
		}
		return 0;
	}

	/**
	 * Charges the money and upgrades to a given HW type. Does nothing if
	 * there's not enough money.
	 * 
	 * @param hwType HW to upgrade to (HW_BASIC, HW_ADVANCED, HW_SUPER)
	 */
	public void upgradeHW(int hwType) {
		if (moneyController.payForHW(hwType)) {
			setHwMultiplier(getHWMultiplierForHWType(hwType));
		}
	}

	/**
	 * Gets the currently active HW type
	 * 
	 * @return HW type (HW_BASIC, HW_ADVANCED, HW_SUPER)
	 */
	public int getHW() {
		if (Math.abs(getHwMultiplier()
				- HWController.getHWMultiplierForHWType(HW_SUPER)) <= 0.05) {
			return HW_SUPER;
		} else if (Math.abs(getHwMultiplier()
				- HWController.getHWMultiplierForHWType(HW_ADVANCED)) <= 0.05) {
			return HW_ADVANCED;
		} else {
			return HW_BASIC;
		}
	}

	/**
	 * Gets the multiplication factor of the current HW
	 * 
	 * @see HWController#getHWMultiplierForHWType(int)
	 *  
	 * @return multiplication factor
	 */
	public float getHwMultiplier() {
		return hwMultiplier;
	}

	private void setHwMultiplier(float hwMultiplier) {
		this.hwMultiplier = hwMultiplier;
	}

	/**
	 * Is the given HW type available?
	 * 
	 * @param hwType HW type
	 * @return true if a given HW type is available
	 */
	public boolean isEnabled(int hwType) {
		if (Math.abs(getHwMultiplier()
				- HWController.getHWMultiplierForHWType(hwType)) <= 0.05
				|| moneyController.getGameState().getMoney() >= getPaymentForHWType(hwType)) {
			if (hwType < getHW()) {
				// no need to enable "old" HW - downgrade not possible
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

}
