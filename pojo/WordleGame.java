package pojo;

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
			if(correct[i]) {
				answer[i] = '0';
				guess[i] = '1';
			}
		}
		for(int i = 0; i < 5; i++) {
			if(correct[i]) continue;
			for(int j = 0; j < 5; j++) {
				contains[i] = (guess[i] == answer[j]);
				if(contains[i]) {
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
			if(correct[i]) out.append("&#129001;");
			else {
				if(contains[i]) out.append("&#129000;");
				else out.append("&#11035;");
			}
		}
		return out.toString();
	}
}
