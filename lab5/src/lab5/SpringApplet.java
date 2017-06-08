package lab5;



	import javax.swing.JApplet; //importujemy potrzebne biblioteki
	import java.awt.*;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.event.MouseMotionListener;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	
	import java.util.Timer;
	// implenetujemy interface-y MouseListener,ActionListener,MouseMotionListener
	public class SpringApplet extends JApplet implements MouseListener,ActionListener,MouseMotionListener {
	//tworzymy pola do przechowywania obiekt�w klas:
	private Timer timer;
	private SimEngine obiekt3;
	private SimTask obiekt4;
	private int x,y; // pola do przechowywania po�o�enia kursora myszy
	boolean mouseDrag; // zmienna w kt�rej przechowywany b�dzie stan myszy
	//Pola do przechowywania element�w interface-u u�ytkownika:
	private TextField t_masa, t_wspolczynnik_sprezystosci, t_wspolczynnik_tlumienia,
	t_przyspieszenie_grawitacyjne, t_dlugosc_swobodna_sprezyny;
	private Label l_masa, l_wspolczynnik_sprezystosci, l_wspolczynnik_tlumienia,
	l_przyspieszenie_grawitacyjne, l_dlugosc_swobodna_sprezyny;
	private Button breset;
	public void mouseDragged(MouseEvent arg0){
	if(mouseDrag==true){ // sprawdzamy czy mysz jest przeci�gana i je�li tak jest, ustawiamy po�o�enie masy zgodnie z pozycj� kursora
	Vector2D p = new Vector2D(arg0.getX(),arg0.getY());
	obiekt3.setpMasy(p);
	repaint(); //wywo�ujemy metode repaint
	arg0.consume();
	}
	System.out.println("mouseDragged");
	}
	public void mouseMoved(MouseEvent arg0) {
	System.out.println("mouseMoved");
	}
	public void mouseEntered(MouseEvent ev) {
	System.out.println("mouseEntered");
	}
	public void mouseClicked(MouseEvent ev) {
	System.out.println("mouseClicked");
	}
	public void mouseExited(MouseEvent ev) {
	System.out.println("mouseExited");
	}
	public void mousePressed(MouseEvent ev) {
		x = ev.getX();
		y = ev.getY();
		// warunek na po�o�enie kursora:
		if (obiekt3.getpMasy().x <= x && x <= obiekt3.getpMasy().x+20 && obiekt3.getpMasy().y<=y && obiekt3.getpMasy().y +20 >= y){
		//wuruchamiamy timer, zmianiamy mouseDrag na true i zerujemy pr�dko�� masy
		timer.cancel();
		timer.purge();
		obiekt3.reset();
		mouseDrag=true;
		//wywo�ujemy metode consume()
		ev.consume();
		}
		System.out.println("mousePressed");
		}
		public void mouseReleased(MouseEvent ev) {
		// je�li mysz zosta�a przeci�gni�ta...
		if(mouseDrag==true){
		//w��czamy timer
		timer = new Timer();
		obiekt4 = new SimTask(obiekt3, this , 0.04);
		timer.scheduleAtFixedRate(obiekt4, 0, 5);
		mouseDrag=false; //zmiana warto�ci typu bool na false (koniec przeci�gania)
		ev.consume();
		}
		System.out.println("mouseReleased");
		}
		// iplementacja interface-u ActionEvent
		public void actionPerformed(ActionEvent ev) {
		//Tworzymy obiekt aby pozna� jaka jest przyczyna zdarzenia
		Object przyczyna = ev.getSource();
		//sprawdzamy, czy przycisk jest przyczyn� zdarzenia:
		if(przyczyna == breset) {
		//w��czamy timer
		timer.cancel();
		timer.purge();
		// zerujemy pr�dko�� masy:
		obiekt3.reset();
		//ustawiamy po�o�enie pocz�tkowe masy
		double pm = obiekt3.getpPunktuZawieszenia().y +
		Double.parseDouble(t_dlugosc_swobodna_sprezyny.getText());
		Vector2D Nowy = new Vector2D(obiekt3.getpPunktuZawieszenia().x, pm);
		obiekt3.setpMasy(Nowy);
		// ustawiamy parametry symulacji:
		obiekt3.setmasa(Double.parseDouble(t_masa.getText()));
		obiekt3.setwspSp(Double.parseDouble(t_wspolczynnik_sprezystosci.getText()));
		obiekt3.setwspTl(Double.parseDouble(t_wspolczynnik_tlumienia.getText()));
		obiekt3.setG(Double.parseDouble(t_przyspieszenie_grawitacyjne.getText()));
		obiekt3.setlSwobodna(Double.parseDouble(t_dlugosc_swobodna_sprezyny.getText()));
		//wywo�anie repaint:
		repaint();
		}
		}
		public void init() {
		setLayout(null);
		mouseDrag=false;
		//dodajemy tzw. "nas�uchiwacz" do myszy, do ruchu myszy oraz przycisku:
		addMouseListener(this);
		addMouseMotionListener(this);
		//obiekty u�ywane po�niej do konstruktora:
		Vector2D pMasy = new Vector2D(200,100);
		Vector2D vMasy = new Vector2D(0,0);
		Vector2D pPunktuZawieszenia = new Vector2D(200,40);
		//tworzymy obiekty:
		obiekt3 = new SimEngine(15,3,2,200,pMasy,vMasy,pPunktuZawieszenia,9.8);
		obiekt4 = new SimTask(obiekt3, this , 0.05);
		timer = new Timer(); //obiekt klasy Timer
		timer.scheduleAtFixedRate(obiekt4, 0, 10); // u�ywamy metody timera
		//inicjujemy pola oraz rozmieszamy je na aplecie:
		t_masa = new TextField("");
		t_masa.setBounds(50,40,50,25);
		t_wspolczynnik_sprezystosci = new TextField("");
		t_wspolczynnik_sprezystosci.setBounds(180,40,50,25);
		t_wspolczynnik_tlumienia = new TextField("");
		t_wspolczynnik_tlumienia.setBounds(290,40,50,25);
		t_przyspieszenie_grawitacyjne= new TextField("");
		t_przyspieszenie_grawitacyjne.setBounds(400,40,50,25);
		t_dlugosc_swobodna_sprezyny = new TextField("");
		t_dlugosc_swobodna_sprezyny.setBounds(510,40,50,25);
		l_masa = new Label("masa");
		l_masa.setBounds(50,20,110,20);
		l_wspolczynnik_sprezystosci = new Label("wsp_sprezystosci");
		l_wspolczynnik_sprezystosci.setBounds(180,20,100,20);
		l_wspolczynnik_tlumienia = new Label("wsp_tlumienia");
		l_wspolczynnik_tlumienia.setBounds(290,20,100,20);
		l_przyspieszenie_grawitacyjne= new Label("przyspieszenie_g");
		l_przyspieszenie_grawitacyjne.setBounds(400,20,100,20);
		l_dlugosc_swobodna_sprezyny= new Label("dl_spo_sprezyny");
		l_dlugosc_swobodna_sprezyny.setBounds(510,20,100,20);
		//dodajemy elementy graficznego interfejsu u�ytkownika:
		add(t_masa);
		add(t_wspolczynnik_sprezystosci);
		add(t_wspolczynnik_tlumienia);
		add(t_przyspieszenie_grawitacyjne);
		add(t_dlugosc_swobodna_sprezyny);
		add(l_masa);
		add(l_wspolczynnik_sprezystosci);
		add(l_wspolczynnik_tlumienia);
		add(l_przyspieszenie_grawitacyjne);
		add(l_dlugosc_swobodna_sprezyny);
		breset = new Button("Reset");
		breset.setBounds(50,80,100,25);
		add(breset);
		breset.addActionListener(this);
		}
		public void paint(Graphics g){
		g.clearRect(0,0,getWidth(),getHeight()); // czy�cimy powierzchnie appletu a nast�pnie rysujemy grafike:
		g.drawLine((int) obiekt3.getpPunktuZawieszenia().x, (int) obiekt3.getpPunktuZawieszenia().y,(int)
		obiekt3.getpMasy().x, (int) obiekt3.getpMasy().y);
		g.drawLine((int) obiekt3.getpPunktuZawieszenia().x-50, (int) obiekt3.getpPunktuZawieszenia().y,(int)
		obiekt3.getpPunktuZawieszenia().x+50, (int) obiekt3.getpPunktuZawieszenia().y);
		g.drawOval((int) obiekt3.getpMasy().x - 15, (int) obiekt3.getpMasy().y, 25,
		20);
		}}



