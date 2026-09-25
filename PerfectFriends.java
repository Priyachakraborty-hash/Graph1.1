import java.util.*;

public class PerfectFriends 
{


public static void gcc(ArrayList<Integer>[] graph,boolean[] vis,int src,ArrayList<Integer> comp)
{
   
    vis[src] = true;
    comp.add(src);
    for(int nbr: graph[src])
    {
          
          if(vis[nbr] == false)
          {
            gcc(graph,vis,nbr,comp);
          }
    }


}
public static int perfectFr(ArrayList<Integer>[] graph)
{
  ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
  boolean[] vis = new boolean[graph.length];
  int count =0;
 for(int v =0;v<vis.length;v++)
  {
        if(vis[v] == false)
        {
         
            ArrayList<Integer> comp = new ArrayList<>();
            gcc(graph,vis,v,comp);
            comps.add(comp);

        }
  }
  //lets try method 1 which is going o(n2),try dry run on copy
  
//   for(int i=0;i<comps.size();i++)
//   {
//     int s1 = comps.get(i).size();
//     for(int j=i+1;j<comps.size();j++)
//     {
//         int s2 = comps.get(j).size();
//         count+=s1*s2;
//     }
//   }
// return count;
//lets try another method which is optimised
//isme last se sum lete hue ana hai,see dry run for comps : {{a},{b},{c},{d},{e},{f}}

int sum = comps.get(comps.size()-1).size();

for(int i = comps.size()-2;i>=0;i--)
{
    int s = comps.get(i).size();
    count = count + sum*s;
    sum = sum+s;
}
return count; // can use long instead of int , if vertices are 100,000.
  
}
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
          System.out.println("Enter vertices");
           int n  = sc.nextInt();//no of vertices
           System.out.println("Enter Edges");
           int k = sc.nextInt(); // no of edges

           ArrayList<Integer>[] graph = new ArrayList[n];
           //provide memory reference at every location
           for(int i =0;i<n;i++)
           {
               graph[i] = new ArrayList<>();
           }
           //create the edges in the graph, k times
           for(int i=0;i<k;i++)
           {
            System.out.println("Enter source");
            int src = sc.nextInt();
            System.out.println("Enter nbr");
             int nbr = sc.nextInt();
             graph[src].add(nbr);
             graph[nbr].add(src);
           }

       //no of pair of perfect friend, ie count so that they both are not in the same club
       int count = perfectFr(graph);
       System.out.println(count);

    }
    
}
