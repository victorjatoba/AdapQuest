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
package managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import util.Constants;
import util.MessageUtil;
import cat.CatManager;
import data.OptionModel;
import data.QuestionModel;

@ManagedBean(name = "exerciseMB")
@SessionScoped
public class ExerciseMB {

	private List<QuestionModel> listQuestionModel;
	private QuestionModel actualQuestion;
	private QuestionModel nextQuestion;
	private OptionModel responseOptionModel;
	private String numberOfOption;
	private CatManager catManager;

	@ManagedProperty(value = "#{userLoginMB}")
	private UserLoginMB userLoginMB;

	public ExerciseMB() {
		this.clearFields();
	}

	public ExerciseMB(QuestionModel actualQuestion) {
		super();
		this.actualQuestion = actualQuestion;
	}

	private void clearFields() {
		// this.fillListQuestionFake();
		userLoginMB = new UserLoginMB();
		this.catManager = new CatManager();
	}

	public String answerQuestion() {

		boolean checked = false;
		boolean userAnswerCorrectly = false;

		for (OptionModel optionModel : this.actualQuestion.getListOptionModel()) {

			if (optionModel.isResponse()) {
				checked = true;
				if (optionModel.isFlagRight()) {
					MessageUtil.addInfoMessage("Parabéns! Você acertou!");
					userAnswerCorrectly = true;
				} else {
					MessageUtil.addErrorMessage("Resposta errada, tente novamente!");
				}
			}
		}

		if (!checked) {
			MessageUtil.addErrorMessage("É necessário escolher uma opção.");
		} else {
			this.nextQuestion = this.catManager.nextQuestion(this.userLoginMB.getUserModel(), this.actualQuestion, userAnswerCorrectly);

			// if (!ValidationUtil.isNullOrEmpty(this.exerciseHistoricalModel.getId())) {
			// this.exerciseHistoricalDAO.update(this.exerciseHistoricalModel);
			// } else {
			// this.exerciseHistoricalDAO.insert(this.exerciseHistoricalModel);
			// }
		}

		return null;
	}

	public String goToNextQuestion() {
		this.actualQuestion = nextQuestion;
		this.nextQuestion = null;

		return Constants.PAGE_EXERCISE;
	}

	public OptionModel getResponseOptionModel() {
		return responseOptionModel;
	}

	public void setResponseOptionModel(OptionModel responseOptionModel) {
		this.responseOptionModel = responseOptionModel;
	}

	public String getNumberOfOption() {
		return numberOfOption;
	}

	public void setNumberOfOption(String numberOfOption) {
		this.numberOfOption = numberOfOption;
	}

	public List<QuestionModel> getListQuestionModel() {
		return listQuestionModel;
	}

	public void setListQuestionModel(List<QuestionModel> listQuestionModel) {
		this.listQuestionModel = listQuestionModel;
	}

	public QuestionModel getActualQuestion() {
		return actualQuestion;
	}

	public void setActualQuestion(QuestionModel actualQuestion) {
		this.actualQuestion = actualQuestion;
	}

	public QuestionModel getNextQuestion() {
		return nextQuestion;
	}

	public void setNextQuestion(QuestionModel nextQuestion) {
		this.nextQuestion = nextQuestion;
	}

	public UserLoginMB getUserLoginMB() {
		return userLoginMB;
	}

	public void setUserLoginMB(UserLoginMB userLoginMB) {
		this.userLoginMB = userLoginMB;
	}
}
