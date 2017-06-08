package lab5;

public class Vector2D {

double x, y;
Vector2D(){
	x=y=0;
}
Vector2D(double _x, double _y){
	x=_x;
	y=_y;
}
double dlugosc(){
	return Math.sqrt(x*x+y*y);
}
Vector2D suma(Vector2D v){
	
	return new Vector2D(x+v.x,y+v.y);
}
Vector2D minus(Vector2D v){
	return new Vector2D(x-v.x,y-v.y);
}
Vector2D mnozenie(double n){
	return (new Vector2D (x*n, y*n));
}
Vector2D normalizacja(){
	return mnozenie(1/dlugosc());
}
public String toString(){
	return " x = " + x + " y = " + y;
}

}

