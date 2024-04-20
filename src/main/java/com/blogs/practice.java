package com.blogs;

public class practice {
    public static void main(String[] args) {
        
                int[][] arr = new int[3][3];
                arr[0][0]=2;
                arr[0][1]=1;
                arr[0][2]=3;
                arr[1][0]=4;
                arr[1][1]=5;
                arr[1][2]=2;
                arr[2][0]=4;
                arr[2][1]=1;
                arr[2][2]=4;
                int i=0;
                int sum=0;
                int j=0;
                for(int i1=0;i1<3;i1++)
                {
                    for(int j1=0;j1<3;j++) {

                        if(i1==j1)
                        {
                            sum= sum+arr[i1][j1];
                        }
                    }
                }
                System.out.println(sum);
            }

        }





