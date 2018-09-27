package assignment;

import java.util.Random;

public class Recommendation {

	/*
	 * Inscrivez votre nom complet (prénom et nom de famille)
	 * ainsi que votre numéro sciper ci-dessous :
	 */
	
	/* Etudiant 1 */
	public static String NAME1 = "Lucas Antelo Blanco";
	public static int SCIPER1 = 235644;
	
	/* Etudiant 2 - laissez tel quel si vous avez cod le projet tout seul */
	public static String NAME2 = "";
	public static int SCIPER2 = 0;
	
	static Random random = new Random();
	
	
	public static String matrixToString(double[][] A) 
	{
		/* Méthode à coder */
		String matrix = "{";
		
		for(int i = 0; i < A.length; ++i)
		{
			matrix += "\n {" + A[i][0];
			for(int j = 1; j < A[i].length; ++j)
			{
				matrix += ","  + A[i][j] ;
			}
			matrix += "},";
		}
		matrix = matrix.substring(0, matrix.length() -1);
		
		matrix += "\n};\n";
		
		return matrix;
	}
	
	public static boolean isMatrix( double[][] A ) 
	{
		
		boolean test = true;
		double sum = 0;
		
		if(A == null)
		{
			return false;
		}
		else
		{
		
			int colNumber = A[0].length;
			for(int i = 0; i < A.length && test; ++i)
			{
				if(colNumber != A[i].length || A[i].length == 0)
				{
					test = false;
				}
				for(int j = 0; j < A[i].length && test; ++j )
				{
					sum += Math.abs(A[i][j]);
				}
			}
			return test && (sum > 0);
		}	
	}
	
	public static double[][] multiplyMatrix(double[][] A, double[][] B) { //U 5x2 and V 2x5
		/* Méthode à coder */
		
		double[][] mat = null;
		
		if(isMatrix(A) && isMatrix(B) && (A[0].length == B.length))
		{
			mat = new double[A.length][B[0].length];
			
			int columnsB = B[0].length;
			
			int rowsA = A.length;
			int columnsA = A[0].length;
			
			//This loop goes through all the rows of A, as the final matrix will have the same amount of rows as A
			for(int n = 0; n < rowsA; ++n)
			{
				//This loop goes through all the columns of B, as the final matrix will have the same amount of columns as B
				for(int m = 0; m < columnsB;  ++m)
				{
					//This loop goes through the columns of A and rows of B, which are of the same magnitude
					for(int i = 0; i < columnsA; ++i)
					{
						//System.out.print(mat1[n][i] + " " + mat2[i][m] + " ,");
						mat[n][m] += A[n][i] * B[i][m];
					}
					//System.out.println("Product of row " + n + " and column " + m + " " + matProduct[n][m]);
				}
			}
		}
		else
		{
			System.out.println("Unfortunately the program was not able to multiply matrices A and B. Look if they are empty and the the number of "
					+ "\ncolumns of the first matrix is equivalent to the number of rows of the second matrix.");
		}
		
		return mat;
	}
	
	public static double[][] createMatrix( int n, int m, int k, int l) {
		/* Méthode à coder */
		/* Utilisez la variable 'random', par exemple */
		
		double[][] matrix = new double[n][m];
		int max, min;
		if(n == 0 || m == 0)
		{
			return null;
		}
		else
		{
			if(k > l)
			{
				max = k;
				min = l;
			}
			else
			{
				max = l;
				min = k;
			}
			
				for(int i = 0; i < n; ++i)
				{
					for(int j = 0; j < m; ++j)
					{
						matrix[i][j] = random.nextInt((max - min)) + min + random.nextDouble();
					}
				}
			
				return matrix;
			
		}
	}
	
	public static double rmse(double[][] M, double[][] P) { 
		// P = UxV
		/* Méthode à coder */	
		
		boolean test = isMatrix(M) && isMatrix(P) && M[0].length == P[0].length && M.length == P.length;
		double value = 0;
		double sum = 0;
		
		if(test)
		{
			for(int rows = 0; rows < M.length; ++rows)
			{
				for(int columns = 0; columns < M[rows].length; ++columns)
				{
					
						if(M[rows][columns] != 0)
						{
							value += Math.pow((M[rows][columns] - P[rows][columns] ), 2);
							++sum;	
						}
					
				}
			}
			
			return Math.sqrt(value/sum);
		}
		else
		{
			return -1;
		}
	}
	
	public static double updateUElem( double[][] M, double[][] U, double[][] V, int r, int s ) {
		//r: row of u and s:row of v
		/* Méthode à coder */ 
		
		
		boolean test = isMatrix(M) && isMatrix(U) && isMatrix(V) && V.length == U[0].length && U.length == M.length && V[0].length == M[0].length;
		
		if(test)
		{
			int colsV = V[0].length;
			int colsU = U[0].length;
			
			double sigmaKS = 0;
			double divisor = 0;
			double num = 0;
			
				for(int j = 0; j < colsV; j++)
				{	
					sigmaKS = 0;
					
					if(M[r][j] != 0)
					{
						for (int k = 0; k < colsU; k++)
						{
							if(k != s)
							{
								sigmaKS += U[r][k] * V[k][j];
							}
						}
					
						num += V[s][j] * (M[r][j] - sigmaKS);
						
						divisor += Math.pow(V[s][j],2);
					
					}
					
				}
				
				
				if(divisor == 0)
				{
					return 0;
				}
				else
				{
					return num/divisor;
				}
		}
		else
		{
			System.out.println("The method updateUElem was unable to perform the update of the elements in the matrix V. Please check that the parameters are not empty and "
					+ "\nthat the number of lignes in V are equivalent to the number of columns in U. And that the size of M equals the multiplied Matrix of U and V.");
			System.exit(0);
			return 0.0;
		}
	}
	
	public static double updateVElem( double[][] M, double[][] U, double[][] V, int r, int s ) {
		/* Méthode à coder */	
		boolean test = isMatrix(M) && isMatrix(U) && isMatrix(V) &&  V.length == U[0].length && M.length == U.length && M[0].length == V[0].length;
		
		double num = 0;
		double sigmaKS = 0;
		double dividend = 0;
		int rowsU = U.length;
		int colsU = U[0].length;
		
		if(test)
		{
		
			for(int i = 0; i < rowsU; ++i)
			{
				sigmaKS = 0;
				
				if(M[i][s] != 0)
				{
					for (int k = 0; k < colsU; ++k)
					{
						if(k != r)
						{
							sigmaKS += U[i][k] * V[k][s];
						}
					}
					
					num +=  U[i][r] *( M[i][s] - sigmaKS);
					
					dividend += Math.pow(U[i][r],2);
				}
			
			}
		
			if(dividend == 0)
			{
				return 0;
			}
			else
			{
			 	return num/dividend;
			}
		}
		else
		{
			System.out.println("The method updateVelem was unable to perform the update of the elements in the matrix V. Please check that the parameters are not empty and "
					+ "\nthat the number of lignes in V are equivalent to the number of columns in U. And that the size of M equals the multiplied Matrix of U and V.");
			System.exit(0);
			return 0.0;
		}
	}
	
	public static double[][] optimizeU( double[][] M, double[][] U, double[][] V) {
		/* Méthode à coder */	
		//This method modifies the arrays directly in a way, that the output is exactly the modified arrays and the programm continues to work with the modified
		//ones instead of the original matrix U.I talked with Barbara Jobstmann and she gave me the permission to do this.
		
		boolean test = isMatrix(M) && isMatrix(U) && isMatrix(V) && U.length == M.length && V[0].length == M[0].length && V.length == U[0].length; 
	
		if(test)
		{

			int rows = U.length;
			int cols = U[0].length;
							
				for(int i = 0; i < rows; ++i)
				{
					for(int j = 0; j < cols; ++j)
					{
						U[i][j] = updateUElem(M, U, V, i, j );
					}
					
				}	
				
			return (multiplyMatrix(U,V));
		}
		else
		{
			System.out.println("Unfortunately the method optimizeU was unable to perform the optimization. Please check your matrices. For example if Matrix M is empty or has just zero values."
					+ "\nMoreover if the number of lignes in U an M  and furthermore the number of columns in V and M are equivalent.");
			System.exit(0);
			return null;
		}
				
	}

	public static double[][] optimizeV( double[][] M, double[][] U, double[][] V) {//Stop optimization when the difference of rmse between M and P is 10E-6
		/* Méthode à coder */	
		//This method modifies the arrays directly in a way, that the output is exactly the modified arrays and the programm continues to work with the modified
		//ones instead of the original matrix U.I talked with Barbara Jobstmann and she gave me the permission to do this.
		
		boolean test = isMatrix(M) && isMatrix(U) && isMatrix(V) && U.length == M.length && V[0].length == M[0].length && V.length == U[0].length; 
		
		if(test)
		{
			
			int rows = V.length;
			int cols = V[0].length;
				
				for(int i = 0; i < rows; ++i)
				{
					for(int j = 0; j < cols; ++j)
					{
						V[i][j] = updateVElem(M, U, V, i, j );
					}
				}
				
		
			return (multiplyMatrix(U,V));
		}
		else
		{
			System.out.println("Unfortunately the method optimizeV was unable to perform the optimization. Please check your matrices. For example if Matrix M is empty or has just zero values."
					+ "\nMoreover if the number of lignes in U an M  and furthermore the number of columns in V and M are equivalent.");
			System.exit(0);
			return null;
		}	
	}
	
	public static int[] recommend( double[][] M, int d) {
		/* Méthode à coder */	
		
		double averageValue = 0;
		int r = 0;
		
		
		
		if(isMatrix(M))
		{
			int rows = M.length;
			int cols = M[0].length;
			
			for(int i = 0; i < rows; ++i)
			{
				for(int j = 0; j < cols; ++j)
				{
					if(M[i][j] != 0)
					{
						averageValue += M[i][j];
						++r;
					}
				}
			}
			
			/*
			 *v is initialized by  (Sigma of M divided by r) -- r is the amount of elements in M whose values are not equal to zero. and this term divided by d - value entered as parameter
			 *in this method. The whole term put in Math.square. The value will be put in int format and you enter this value in createMatrix as third parameter, and the fourth
			 *parameter is that value plus 1, as you can verify below.
			*/
			
			if(r != 0)
			{
				averageValue = Math.sqrt((averageValue/ (double)r)/d);
			}
			else
			{
				averageValue = 1;
			}
			
			double[][] V = createMatrix(d, cols, (int)averageValue, (int)averageValue + 1);
			double[][] U = createMatrix(rows, d, 0, 1);
	
			//change the values of the matrix by a constant c!!! --> not necessary, as the method createMatrix already implements random.nextDouble() in this procedure

			double delta = 0;
			double condition = 0.000001;
			double rmseEnd = rmse(M , multiplyMatrix(U,V));
			double rmseStart = 0;
			
			do{		
				
				rmseStart = rmseEnd;
				optimizeU(M, U, V);
				optimizeV(M,U,V);
				rmseEnd = rmse(M, multiplyMatrix(U,V));	
				delta = rmseStart - rmseEnd;

			}while(delta > condition); 
			
		
			
			double[][] P = multiplyMatrix(U,V);
			
			int[] recommendation = new int[rows];
			double max = 0;
			int index = -1;
			for(int i = 0; i < rows; ++i)
			{
				max = 0;
				
				index = -1;
				
				for(int j = 0; j < cols; ++j)
				{	
					
					
					if(M[i][j] == 0)
					{
						
						if(P[i][j] > max)
						{
							max = P[i][j];
							index = j;
						}
						
						
					}
					
				}
				
				recommendation[i] = index;
				
			}
			
			return recommendation;
		}		
		else
		{
			
			System.out.println("Unfortunately your provided Matrix seems to have some incongruencies. Check if the number of columns on each line is consistent " 
			+ "\n and moreover if not all the entries in the matrix are equal to zero. This would be considered as an empty matrix for this program");
			
			System.exit(0);
			return null;
			
		}
		
	}
}


