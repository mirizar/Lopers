
public class Tijd implements Comparable <Tijd>{
/** nog een beetje comentaar */
	/**
	 * @param args
	 */
	
	private int uur;
	private int minuut;
	private int seconde;
	
	public Tijd(int uur, int minuut, int seconde) throws IllegalArgumentException{
		setUur(uur);
		setMinuut(minuut);
		setSeconde(seconde);
	}
	
	public Tijd(int seconden) throws IllegalArgumentException {
		if (seconden < 0 || seconden > 24 * 60 * 60){
			throw new IllegalArgumentException("...");
		}
		int uur = seconden / 3600;
	    int minuut = seconden % 3600 / 60;
	    int seconde = seconden % 3600 % 60;
		
	    setUur(uur);
		setMinuut(minuut);
		setSeconde(seconde);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + minuut;
		result = prime * result + seconde;
		result = prime * result + uur;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tijd other = (Tijd) obj;
		if (minuut != other.minuut)
			return false;
		if (seconde != other.seconde)
			return false;
		if (uur != other.uur)
			return false;
		return true;
	}

	@Override
	public String toString(){
		return String.format("%d:%02d:%02d %s",
				((uur == 0 || uur == 12)?12:uur % 12),minuut,seconde,(uur < 12? "AM":"PM"));
	}
	
	
	public int getUur(){
		return uur;
	}
	
	public void setUur(int uur){
		this.uur = uur;
	}
	
	
	public int getMinuut() {
		return minuut;
	}

	public void setMinuut(int minuut) {
		this.minuut = minuut;
	}

	public int getSeconde() {
		return seconde;
	}

	public void setSeconde(int seconde) throws IllegalArgumentException{
		if (seconde < 0 || seconde > 59){
			throw new IllegalArgumentException("Seconde moet tussen 0 en 59 liggen");
		}
		this.seconde = seconde;
	}
	
	public int getTijdInSeconden(){
		return uur * 3600 + minuut * 60 + seconde;
	}
	
	public String getTijd() {
		return String.format("%d uur %02d minuten %02d seconden",
				uur ,minuut,seconde);
	}
	
	public Tijd verschilInTijd(Tijd tijd){
		int seconden = Math.abs(this.getTijdInSeconden() - tijd.getTijdInSeconden());
		return new Tijd(seconden);
	}

	public static void main(String[] args) {
		Tijd t1 = new Tijd(2,10,10);
		Tijd t2 = new Tijd(7420);
		System.out.println(t1);
		System.out.println(t2);
		Tijd verschil = t1.verschilInTijd(t2);
		System.out.println("Verschil is "+verschil.getTijd());
		
		String tijdSituering = t1.compareTo(t2) > 0?" later dan ":t1.compareTo(t2) <0 ?" vroeger dan ":" gelijk "; 
		
		System.out.println(t1 + tijdSituering + t2);
		

	}

	@Override
	public int compareTo(Tijd tijd) {
		
		return this.getTijdInSeconden() - tijd.getTijdInSeconden();
	}

}
