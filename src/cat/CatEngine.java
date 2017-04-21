/*
 * Copyright 2017 V. M. G. Jatobá
 *
 * Licensed under the MIT;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://opensource.org/licenses/MIT
 *
 * The software is provided "AS IS", without warranty of any kind, 
 * express or implied, including but not limited to the warranties
 * of merchantability, fitness for a particular purpose and
 * noninfringement. In no event shall the authors or copyright
 * holders be liable for any claim, damages or other liability,
 * whether in an action of contract, tort or otherwise, arising
 * from, out of or in connection with the software or the use
 * or other dealings in the software.
 */
package cat;

import javax.faces.bean.ManagedProperty;

import managedBean.UserLoginMB;
import util.Constants;

/**
 * 
 * @author victorjatoba
 *
 */
public class CatEngine {

	@ManagedProperty(value = "#{userLoginMB}")
	private UserLoginMB userLoginMB;

	public void start() {
		if (userLoginMB.isUserLogged()) {
			if (!haveStudentEnoughInformation()) {
				this.selectItemByLevel(Constants.ITEM_LEVEL_MEDIUM);
			} else {
				this.selectItemByFit();
			}

			this.showItem();

		} else {
			// show User not logged Error Message
		}
	}

	public void answer() {
		boolean userHit = checkAnswer();
		markItemAsAnswered();
		updateProficiency();

		if (!finalizationCriteria()) {
			if (userHit) {
				selectItemByLevel(Constants.ITEM_LEVEL_HARD);
			} else {
				selectItemByLevel(Constants.ITEM_LEVEL_EASY);
			}

			this.showItem();
		} else {
			this.finalizeTest();
			this.showFeedback();
		}

	}

	private void showFeedback() {
		// TODO Auto-generated method stub

	}

	private void finalizeTest() {
		// TODO Auto-generated method stub

	}

	private void updateProficiency() {
		// TODO Auto-generated method stub

	}

	private void markItemAsAnswered() {
		// TODO Auto-generated method stub

	}

	private boolean checkAnswer() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean finalizationCriteria() {
		// TODO Auto-generated method stub
		return false;
	}

	private void showItem() {
		// TODO Auto-generated method stub

	}

	private void selectItemByFit() {
		// TODO Auto-generated method stub

	}

	private void selectItemByLevel(int itemLevelMedium) {
		// TODO Auto-generated method stub

	}

	private boolean haveStudentEnoughInformation() {
		// TODO Auto-generated method stub
		return false;
	}

	public UserLoginMB getUserLoginMB() {
		return userLoginMB;
	}

	public void setUserLoginMB(UserLoginMB userLoginMB) {
		this.userLoginMB = userLoginMB;
	}

}
