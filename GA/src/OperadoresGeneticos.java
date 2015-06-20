import java.util.*;

public class OperadoresGeneticos {
	
	public OperadoresGeneticos(){
		
	}
	
	/*
	 * M�todo de mutaci�n swapMutation
	 * @param fenotipo
	 * @return fenotipo
	 */
	public int[][] swapMutation(int[] genotipo1, int[] genotipo2){
		
		int[][] hijos = new int [2][10];
		Random rnd = new Random();
		int random1 = rnd.nextInt(genotipo1.length);
		int random2 = rnd.nextInt(genotipo2.length);
		int temp1 = 0;
		
		while(random1 == random2){
			random2 = rnd.nextInt(genotipo2.length);
		}
		
		hijos[0] = genotipo1.clone();
		hijos[1] = genotipo2.clone();
		
		temp1 = hijos[0][random1];
		hijos[0][random1] = hijos[0][random2];
		hijos[0][random2] = temp1;
		
		temp1 = hijos[1][random1];
		hijos[1][random1] = hijos[1][random2];
		hijos[1][random2] = temp1;
		
		return hijos;
	}
	
	/*
	 * M�todo de mutaci�n Inversin Mutation
	 * @param fenotipo
	 * @return fenotipo
	 */
	public int[][] inversionMutation(int[] genotipo1, int[] genotipo2){
		
		int[][] hijos = new int [2][10];
		int[] hijo1 = genotipo1.clone();
		int[] hijo2 = genotipo2.clone();
		// Seleccionar 2 puntos al azar
		Random rnd = new Random();
		int random1 = rnd.nextInt(genotipo1.length);
		int random2 = rnd.nextInt(genotipo2.length);
		// Asegurar que son distintos
		while(random1 == random2){
			random1 = rnd.nextInt(genotipo1.length);
			random2 = rnd.nextInt(genotipo2.length);
		}
		// Hacer que el m�s peque�o sea el p1 y el m�s grande p2
		int p1 = Math.min(random1, random2);
		int p2 = Math.max(random1, random2);
		// Invertir entre el m�nimo y el m�ximo para el hijo1
		for (int i = 0; i < ((p2-p1+1)/2); i++) {
			int aux = hijo1[p1 + i];
			hijo1[p1 + i] = hijo1[p2 - i];
			hijo1[p2 - i] = aux;
		}
		// Invertir entre el m�nimo y el m�ximo para el hijo2
		for (int i = 0; i < ((p2-p1+1)/2); i++) {
			int aux = hijo2[p1 + i];
			hijo2[p1 + i] = hijo2[p2 - i];
			hijo2[p2 - i] = aux;
		}
		hijos[0] = hijo1;
		hijos[1] = hijo2;
		return hijos;
		
	}
	
	/*
	 * M�todo de cruce Order Crossover
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] orderCrossover(int[]padre1, int[]padre2){
				
		// Declaraci�n matriz de hijos
		
		int[][] hijos = new int [2][padre1.length];
		
		// Declaraci�n vector de cada hijo
		
		int[] hijo1 = new int [padre1.length];
		int[] hijo2 = new int [padre2.length];
		
		Random random = new Random();
		
		// Obtener el tama�o del fenotipo1
		
		int tamano = padre1.length;
		
		// Selecciona dos n�meros aleatorios para el punto de inicio y fin del corte
		
		int numero1 = random.nextInt(tamano);
		int numero2 = random.nextInt(tamano);
		
		// Hacer que el m�s peque�o sea el inicio y el m�s grande el final
		
		int inicio = Math.min(numero1, numero2);
		int fin = Math.max(numero1, numero2);
		
		// Inicializa el vector hijo1 en -1 en cada posici�n
		
		for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = -1;
		}
		
		// Selecciona y copia el subtour dentro del Padre1 en el hijo1
		
		for (int i = inicio; i <= fin; i++) {
			hijo1[i] = padre1[i];
		}
		
		// Copia de un vector temporal para el padre2
		
		int[] padre2copia = padre2.clone();
		
		// Borra el subtour escogido en el padre1 dentro del padre2copia
		
		for (int i = inicio; i <= fin; i++) {
			for (int j = 0; j < padre2copia.length; j++) {
				if (padre2copia[j] == padre1[i]){
					padre2copia[j] = -1;
				}
			}
		}
		
		// Ubica las ciudades en las posiciones libres del hijo1 de izquierda a derecha
		
		int j = 0;
		for (int i = 0; i < hijo1.length; i++) {
			if(hijo1[i] == -1){
					if(padre2copia[j] != -1){
						hijo1[i] = padre2copia[j];
						j++;
					}
					else{
						j++;
						i--;
					}
			}
			else{
				hijo1[i] = hijo1[i];
			}
		}

		/*
		 * Pasos para crear el hijo2
		 */
		// Inicializa el vector hijo2 en -1 en cada posici�n
		
		for (int i = 0; i < hijo2.length; i++) {
			hijo2[i] = -1;
		}
		
		// Selecciona y copia el subtour dentro del Padre2 en el hijo2
		
		for (int i = inicio; i <= fin; i++) {
			hijo2[i] = padre2[i];
		}
		
		// Copia de un vector temporal para el padre2
		
		int[] padre1copia = padre1.clone();
		
		// Borra el subtour escogido en el padre2 dentro del padre1copia
		
		for (int i = inicio; i <= fin; i++) {
			for (int y = 0; y < padre1copia.length; y++) {
				if (padre1copia[y] == padre2[i]){
					padre1copia[y] = -1;
				}
			}
		}
		
		// Ubica las ciudades en las posiciones libres del hijo2 de izquierda a derecha
		
		j = 0;
		for (int i = 0; i < hijo2.length; i++) {
			if(hijo2[i] == -1){
				if(padre1copia[j] != -1){
					hijo2[i] = padre1copia[j];
					j++;
				}
				else{
					j++;
					i--;
				}
			}
			else{
				hijo2[i] = hijo2[i];
			}
		}
				
		hijos[0] = hijo1;
		hijos[1] = hijo2;
		
		return hijos;
	}
	
	/*
	 * M�todo de cruce Order preserving one-point crossover
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] OrderBasedCrossover(int[]padre1, int[]padre2){
		
		// Declaraci�n matriz de hijos
		
		int[][] hijos = new int [2][padre1.length];
				
		// Declaraci�n vector de cada hijo
				
		int[] hijo1 = new int [padre1.length];
		int[] hijo2 = new int [padre2.length];
				
		Random random = new Random();
				
		// Obtener el tama�o del fenotipo1
				
		int tamano = padre1.length;
				
		// Selecciona dos n�meros aleatorios para el punto de inicio y fin del corte
				
		int numero = random.nextInt(tamano);
				
		// Inicializa el vector hijo1 en -1 en cada posici�n
				
		for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = -1;
		}
				
		// Selecciona y copia el subtour dentro del Padre1 en el hijo1
				
		for (int i = 0; i <= numero; i++) {
			hijo1[i] = padre1[i];
		}
				
		// Copia de un vector temporal para el padre2
				
		int[] padre2copia = padre2.clone();
				
		// Borra el subtour escogido en el padre1 dentro del padre2copia
				
		for (int i = 0; i <= numero; i++) {
			for (int j = 0; j < padre2copia.length; j++) {
				if (padre2copia[j] == padre1[i]) {
					padre2copia[j] = -1;
				}
			}
		}
		
		// Ubica las ciudades en las posiciones libres del hijo1 de izquierda a derecha
				
		int j = 0;
		for (int i = 0; i < hijo1.length; i++) {
			if(hijo1[i] == -1){
				if(padre2copia[j] != -1){
					hijo1[i] = padre2copia[j];
					j++;
				}
				else{
					j++;
					i--;
				}
			}
			else{
				hijo1[i] = hijo1[i];
			}
		}
		
		/*
		 * Pasos para crear el hijo2
		 */
		// Inicializa el vector hijo2 en -1 en cada posici�n
		
		for (int i = 0; i < hijo2.length; i++) {
			hijo2[i] = -1;
		}
		
		// Selecciona y copia el subtour dentro del Padre2 en el hijo2
		
		for (int i = 0; i <= numero; i++) {
			hijo2[i] = padre2[i];
		}
		
		// Copia de un vector temporal para el padre2
		
		int[] padre1copia = padre1.clone();
		
		// Borra el subtour escogido en el padre2 dentro del padre1copia
		
		for (int i = 0; i <= numero; i++) {
			for (int y = 0; y < padre1copia.length; y++) {
				if (padre1copia[y] == padre2[i]){
					padre1copia[y] = -1;
				}
			}
		}
		
		// Ubica las ciudades en las posiciones libres del hijo2 de izquierda a derecha
		
		j = 0;
		for (int i = 0; i < hijo2.length; i++) {
			if(hijo2[i] == -1){
				if(padre1copia[j] != -1){
					hijo2[i] = padre1copia[j];
					j++;
				}
				else{
					j++;
					i--;
				}
			}
			else{
				hijo2[i] = hijo2[i];
			}
		}
				
		hijos[0] = hijo1;
		hijos[1] = hijo2;
				
		return hijos;
	}
	
	/*
	 * M�todo de cruce LOX Crossover
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] LOXCrossover(int[]padre1, int[]padre2){
				
		// Declaraci�n matriz de hijos
		
		int[][] hijos = new int [2][padre1.length];
		
		// Declaraci�n vector de cada hijo
		
		int[] hijo1 = new int [padre1.length];
		int[] hijo2 = new int [padre2.length];
		
		Random random = new Random();
		
		// Obtener el tama�o del fenotipo1
		
		int tamano = padre1.length;
		
		// Selecciona dos n�meros aleatorios para el punto de inicio y fin del corte
		
		int numero1 = random.nextInt(tamano);
		int numero2 = random.nextInt(tamano);
		
		// Hacer que el m�s peque�o sea el inicio y el m�s grande el final
		
		int inicio = Math.min(numero1, numero2);
		int fin = Math.max(numero1, numero2);
		
		// Inicializa el vector hijo1 en -1 en cada posici�n
		
		for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = -1;
		}
		
		// Selecciona y copia el subtour dentro del Padre1 en el hijo1
		
		for (int i = inicio; i <= fin; i++) {
			hijo1[i] = padre1[i];
		}
		
		// Copia de un vector temporal para el padre2
		
		int[] padre2copia = padre2.clone();
		
		// Borra el subtour escogido en el padre1 dentro del padre2copia
		
		for (int i = inicio; i <= fin; i++) {
			for (int j = 0; j < padre2copia.length; j++) {
				if (padre2copia[j] == padre1[i]){
					padre2copia[j] = -1;
				}
			}
		}
		
		// Ubica las ciudades en las posiciones libres del hijo1 de izquierda a derecha
		
		int j = 0;
		for (int i = 0; i < hijo1.length; i++) {
			if(hijo1[i] == -1){
					if(padre2copia[j] != -1){
						hijo1[i] = padre2copia[j];
						j++;
					}
					else{
						j++;
						i--;
					}
			}
			else{
				hijo1[i] = hijo1[i];
			}
		}

		/*
		 * Pasos para crear el hijo2
		 */
		// Inicializa el vector hijo2 en -1 en cada posici�n
		
		for (int i = 0; i < hijo2.length; i++) {
			hijo2[i] = -1;
		}
		
		// Selecciona y copia el subtour dentro del Padre2 en el hijo2
		
		for (int i = inicio; i <= fin; i++) {
			hijo2[i] = padre2[i];
		}
		
		// Copia de un vector temporal para el padre2
		
		int[] padre1copia = padre1.clone();
		
		// Borra el subtour escogido en el padre2 dentro del padre1copia
		
		for (int i = inicio; i <= fin; i++) {
			for (int y = 0; y < padre1copia.length; y++) {
				if (padre1copia[y] == padre2[i]){
					padre1copia[y] = -1;
				}
			}
		}
		
		// Ubica las ciudades en las posiciones libres del hijo2 de izquierda a derecha
		
		j = 0;
		for (int i = 0; i < hijo2.length; i++) {
			if(hijo2[i] == -1){
				if(padre1copia[j] != -1){
					hijo2[i] = padre1copia[j];
					j++;
				}
				else{
					j++;
					i--;
				}
			}
			else{
				hijo2[i] = hijo2[i];
			}
		}
				
		hijos[0] = hijo1;
		hijos[1] = hijo2;
		
		return hijos;
	}
	
}
