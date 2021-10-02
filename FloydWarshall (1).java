import java.util.ArrayList;
import java.util.List;

public class FloydWarshall {
    public static void main(String[] args) {
        int tableNumber = 0;
        double inf = Double.POSITIVE_INFINITY;
        double[][] baseTable = {{0,2.2,3.2,inf,inf,inf,inf,inf,inf,inf,inf}, {2.2,0,inf,1.1,inf,inf,inf,inf,inf,inf,inf},{3.2,inf,0,inf,6,1.4,4.1,inf,inf,inf,inf},{inf,1.1,inf,0,4.1,inf,inf,inf,inf,inf,inf},{inf,inf,6,4.1,0,inf,inf,inf,inf,inf,inf},{inf,inf,1.4,inf,inf,0,3.1,inf,3.4,inf,inf},{inf,inf,4.1,inf,inf,3.1,0,2.3,inf,inf,inf},{inf,inf,inf,inf,inf,inf,2.3,0,4.4,inf,inf},{inf,inf,inf,inf,inf,3.4,inf,4.4,0,2.3,6.3},{inf,inf,inf,inf,inf,inf,inf,inf,2.3,0,4.3},{inf,inf,inf,inf,inf,inf,inf,inf,6.3,4.3,0}}; //original
        int n = baseTable.length;
        int y, z;
        boolean negativeCycle = false;

        List list = new ArrayList<>();          // MAKE LIST TO HOLD MATRICES
        list.add(baseTable);                    //ADD BASE TABLE

        //ANALYZE PATHS USING FLOYD-WARSHALL
        for (int k = 0; k < n; k++) {
            double[][] newArray = new double[n][n];
            double[][] prevArray = (double[][]) list.get(tableNumber);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (prevArray[i][j] <= (prevArray[i][k] + prevArray[k][j])) {
                        newArray[i][j] = prevArray[i][j];
                    } else {
                        newArray[i][j] = (prevArray[i][k] + prevArray[k][j]);
                    }
                }
            }
            list.add(newArray);             //ADD NEWEST TABLE
            tableNumber++;
        }
        //ITERATE A SECOND TIME SO WE CAN CHECK FOR NEGATIVE CYCLES
        for (int k = 0; k < n; k++) {
            double[][] newArray = new double[n][n];
            double[][] prevArray = (double[][]) list.get(tableNumber);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (prevArray[i][j] <= (prevArray[i][k] + prevArray[k][j])) {
                        newArray[i][j] = prevArray[i][j];
                    } else {
                        newArray[i][j] = (prevArray[i][k] + prevArray[k][j]);
                    }
                }
            }
            list.add(newArray);
            tableNumber++;
        }

        //check for negative cycle
        double[][] firstArray = (double[][]) list.get(n);
        double[][] secondArray = (double[][]) list.get(n+1);
        y = z = 0;
        while(y < n && !negativeCycle) {
            while (z < n && !negativeCycle) {
                if (secondArray[y][z] < firstArray[y][z]) {
                    negativeCycle = true;
                }
                z++;
            }
            y++;
        }
        //PRINT IF NEG CYCLE ELSE PRINT TABLES
        if(negativeCycle) {
            System.out.println("Negative cycle exists");
        }else{
            int k =0;
            for(int q =0; q<=n+1;q++) {
                double[][] array = (double[][]) list.get(q);
                System.out.println(" ");
                System.out.println("Table Number " + k++);
                System.out.println(" ");
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(array[i][j] + ", ");
                    }
                    System.out.println("");
                }
            }
        }
    }

}



