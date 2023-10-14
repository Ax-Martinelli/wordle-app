package datamodel;

public class WordleGame {
	
	private String answerStr;
	private boolean[] correct;
	private boolean[] contains;
	
	public WordleGame(String answerStr) {
		this.answerStr = answerStr;
		this.correct = new boolean[5];
		this.contains = new boolean[5];
	}
	
	public void guess(String guessStr) {
		char[] guess = guessStr.toCharArray();
		char[] answer = answerStr.toCharArray();
		
		for(int i = 0; i < 5; i++) {
			correct[i] = (answer[i] == guess[i]);
			if(correct[i]) answer[i] = '0';
		}
		for(int i = 0; i < 5; i++) {
			if(correct[i]) continue;
			for(int j = i; j < 5; j++) {
				contains[j] = (guess[i] == answer[j]);
				if(contains[j]) {
					answer[j] = '0';
					break;
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		for(int i = 0; i < 5; i++) {
			if(correct[i]) out.append(" correct");
			else {
				if(contains[i]) out.append(" contains");
				else out.append(" wrong");
			}
		}
		return out.toString();
	}
}
