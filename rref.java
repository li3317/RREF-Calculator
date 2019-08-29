import java.util.Scanner;

public class rrefCalcu {

    public static double [][] matrix;
    public static int m;
    public static int n;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);


        //ask m x n
        System.out.println("How many rows? ");
        m = in.nextInt(); //rows

        System.out.println("How many column? ");
        n = in.nextInt(); //columns

        //create m x n 2d array, first row then column
        matrix = new double[m][n];

        System.out.println("Fill in matrix. for example, row 1 column 1, then row 1 column 2 , etc. ");


        //this matrix can have m pivots

        //fill in array
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = in.nextInt();
            }
        }

        //amount of times it needs to be pivot depends on rows and columns, except for some exceptions
        int bigger = (m > n) ? n : m;

        //operate by povit
        for(int row = 0; row < bigger ; row++){ //big problem , haven't figure out how many times pivot is needed
            createPivot(row);
            eliminate(row);
        }




        printMatrix();
    }

    public static void printMatrix(){
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(j % n == 0){
                    System.out.println();
                }
                System.out.print(matrix[i][j] + " ");
            }
        }
    }


    //create pivot in row
    public static void createPivot(int pivot){ //argument is pivot position, index not actual amount

        //exception where last row is all 0
        if(matrix[pivot][pivot] == 0 && pivot == m - 1)
            return;

        //check if pivot = 0
        if(matrix[pivot][pivot] == 0){
            //find a row that starts with not 0

            int swap = 0; // swapping only goes down
            for(int row = pivot + 1; row < m; row++){
                if (matrix[row][pivot] != 0)
                    swap = row;
                break; //swap is row number

            }

            for(int i = 0; i < n; i++){
                double temp = matrix[pivot][i];
                matrix[pivot][i] = matrix[swap][i];
                matrix[swap][i] = temp;
            }

        }


        //divide the row by its pivot
        double divide = matrix[pivot][pivot] / 1.0;
        for(int i = 0; i < n; i++){
            matrix[pivot][i] = matrix[pivot][i] / divide;
        }
    }


    //eliminate all the entries on a column except pivot
    public static void eliminate(int pivot){

        for(int i = 0; i < m; i++) {
            if(i == pivot) //don't touch the row with pivot
                continue;
            if (matrix[i][pivot] != 0) {

                //before changing row save the pivot substrating material
                double temp = matrix[i][pivot];
                for (int j = 0; j < n; j++) { //every row, subtract the multiples of the row with pivot

                    matrix[i][j] = matrix[i][j] - (matrix[pivot][j] * temp);
                    //System.out.println("j = " + j + " " + matrix[i][j]);

                }
            }

        }
    }



}
