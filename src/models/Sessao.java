package models;

public class Sessao {

	private static Estimativa estimativa;

	public static Estimativa getEstimativa() {
		return estimativa;
	}

	public static void setEstimativa(Estimativa estimativa) {
		Sessao.estimativa = estimativa;
	}

}
