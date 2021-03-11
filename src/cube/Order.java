package cube;

public class Order {
	
	//define operations
	static int[][] F = opF();
	static int[][] B = opB();
	static int[][] L = opL();
	static int[][] R = opR();
	static int[][] U = opU();
	static int[][] D = opD();

	public static void main(String[] args) {

		//----------enter macro------------
		int[][][] operation = {R,U,R,D,R,U};
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		//output order
		System.out.println(findOrder(operation));
	}

	
	private static int findOrder(int[][][] operation) {

		int length=operation.length;
		//generate overall matrix
		int[][] overallOp = new int[48][48];
		
		if(length==1) {
			overallOp=operation[0];
		}
		else {
			overallOp=operation[0];
			for(int i=1; i<length; i++) {
				overallOp=multiplyMatrices(overallOp,operation[i],48,48,48);
			}
		}

		//count order
		int count=1;

		int[][] tempMatrix = overallOp;
		while(true){
			//test if matrix is the identity
			if(isIdentity(tempMatrix)) {
				break;
			}
			count++;
			tempMatrix=multiplyMatrices(tempMatrix, overallOp, 48,48,48);
			}
		
		return count;
		}
	
	private static boolean isIdentity(int[][] matrix) {
		
		for(int i=0; i<48; i++) {
			for(int j=0; j<48; j++) {
				if(matrix[i][j] != (i == j ? 1 : 0))
					return false;
			}
		}

		return true;
	}

	private static int[][] opF() {
		int[][] op = new int[48][48];
	//fill diagonal with 1
		for(int i=0; i<48; i++) {
			op[i][i]=1;
		}
	//fill in face operation	
		for(int i = 0; i < 8; i++) {
			int j=(i+6)%8;
			op[i][j]=1;
			op[i][i]=0;
		}	
	//fill in side operation
		int[] set = {34,33,32,28,27,26,46,45,44,8,15,14};
		for(int i=0; i<12; i++) {
			int j=(i+9)%12;
			op[set[i]][set[j]]=1;
			op[set[i]][set[i]]=0;
		}
		return op;
	}
	
	private static int[][] opB() {
		int[][] op = new int[48][48];
	//fill diagonal with 1
		for(int i=0; i<48; i++) {
			op[i][i]=1;
		}
	//fill in face operation	
		for(int i = 0; i < 8; i++) {
			int j=(i+6)%8+16;
			op[i+16][j]=1;
			op[i+16][i+16]=0;
		}	
	//fill in side operation
		int[] set = {38,37,36,12,11,10,42,41,40,24,31,30};
		for(int i=0; i<12; i++) {
			int j=(i+9)%12;
			op[set[i]][set[j]]=1;
			op[set[i]][set[i]]=0;
		}
		return op;
	}
	
	private static int[][] opR() {
		int[][] op = new int[48][48];
	//fill diagonal with 1
		for(int i=0; i<48; i++) {
			op[i][i]=1;
		}
	//fill in face operation	
		for(int i = 0; i < 8; i++) {
			int j=((i+6)%8)+8;
			op[i+8][j]=1;
			op[i+8][i+8]=0;
		}	
	//fill in side operation
		int[] set = {36,35,34,4,3,2,44,43,42,16,23,22};
		for(int i=0; i<12; i++) {
			int j=(i+9)%12;
			op[set[i]][set[j]]=1;
			op[set[i]][set[i]]=0;
		}
		return op;
	}
	
	private static int[][] opL() {
		int[][] op = new int[48][48];
	//fill diagonal with 1
		for(int i=0; i<48; i++) {
			op[i][i]=1;
		}
	//fill in face operation	
		for(int i = 0; i < 8; i++) {
			int j=((i+6)%8)+24;
			op[i+24][j]=1;
			op[i+24][i+24]=0;
		}	
	//fill in side operation
		int[] set = {32,39,38,20,19,18,40,47,46,0,7,6};
		for(int i=0; i<12; i++) {
			int j=(i+9)%12;
			op[set[i]][set[j]]=1;
			op[set[i]][set[i]]=0;
		}
		return op;
	}

	private static int[][] opD() {
		int[][] op = new int[48][48];
	//fill diagonal with 1
		for(int i=0; i<48; i++) {
			op[i][i]=1;
		}
	//fill in face operation	
		for(int i = 0; i < 8; i++) {
			int j=((i+6)%8)+32;
			op[i+32][j]=1;
			op[i+32][i+32]=0;
		}	
	//fill in side operation
		int[] set = {22,21,20,30,29,28,6,5,4,14,13,12};
		for(int i=0; i<12; i++) {
			int j=(i+9)%12;
			op[set[i]][set[j]]=1;
			op[set[i]][set[i]]=0;
		}
		return op;
	}
	
	private static int[][] opU() {
		int[][] op = new int[48][48];
	//fill diagonal with 1
		for(int i=0; i<48; i++) {
			op[i][i]=1;
		}
	//fill in face operation	
		for(int i = 0; i < 8; i++) {
			int j=((i+6)%8)+40;
			op[i+40][j]=1;
			op[i+40][i+40]=0;
		}	
	//fill in side operation
		int[] set = {2,1,0,26,25,24,18,17,16,10,9,8};
		for(int i=0; i<12; i++) {
			int j=(i+9)%12;
			op[set[i]][set[j]]=1;
			op[set[i]][set[i]]=0;
		}
		return op;
	}
	private static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix, int r1, int c1, int c2) {
	       int[][] product = new int[r1][c2];
	       for(int i = 0; i < r1; i++) {
	           for (int j = 0; j < c2; j++) {
	               for (int k = 0; k < c1; k++) {
	                   product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
	               }
	           }
	       }
	       return product;
	    }
	
	
	private static void displayMatrix(int[][] product) {
        System.out.println("The Matrix is: ");
        for(int[] row : product) {
            for (int column : row) {
                System.out.print(column + "    ");
            }
            System.out.println();
        }
    }

}
