package lab5;
import java.util.TimerTask;
public class SimTask extends TimerTask { //dziedziczymy z klasy TimerTask
//pola do przechowywania obiekt�w klas
private SimEngine obiekt1;
private SpringApplet obiekt2;
private double t;
//konstruktor z parametrami:
public SimTask(SimEngine obiekt1, SpringApplet obiekt2, double t){
this.obiekt1=obiekt1;
this.obiekt2=obiekt2;
this.t=t;
}
public void run() { //przeci��ona metoda run
obiekt1.symulacjaUkladu(t); //wywo�ujemy krok
obiekt2.repaint(); // oraz od�wie�amy powierzchnie apletu
}
}