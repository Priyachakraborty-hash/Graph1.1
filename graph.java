import java.util.*;

public class graph 
{
     //ye Edge class hai jiska hum arraylist banayenge
    
    public static class Edge
    {
        int src;
        int nbr;
        int wt;

        public Edge(int vertex1,int vertex2,int weight)
        {
            this.src = vertex1;
            this.nbr = vertex2;
            this.wt = weight;

        }

    }

//jesa ki int array ke lia integer wala array banate hai, usi tarah hum  Arraylist hai usme balki
//edge type ka store krna hai to arraylist ka array banayenge.


public static void display(ArrayList<Edge> graph[])
{
     for(int i=0;i<graph.length;i++)
     {
        System.out.print("["+ i + "] -> ");
         for(int v = 0;v<graph[i].size();v++)
         {
               Edge e = graph[i].get(v);
               System.out.print(e.src+" - "+e.nbr+" @ "+e.wt+" , ");
         }
         System.out.println();
         System.out.println();
     }
     

}
public static class Pair implements Comparable<Pair>
{
    int wsf;
    String psf;
    Pair(int wsf, String psf)
    {
        this.wsf = wsf;
        this.psf = psf;

    }
    public int compareTo(Pair o)
    {
       return this.wsf - o.wsf;
    }
}


public static boolean hasPath(ArrayList<Edge>[] graph,int src, int dst,boolean[] vis)
{
    if(src == dst)
    {
        return true;

    }
    vis[src] = true;
    for(Edge e : graph[src])
    {
       int nbr = e.nbr;
       if(vis[nbr]== false)
        {
            boolean res = hasPath(graph,nbr,dst,vis);
            if(res == true)
            {
                 return true;
            }
        }

    }
    return false;


}

public static void printAllPath(ArrayList<Edge>[] graph,boolean[] vis,int src,int dst,String psf,int wsf)
{
   if(src==dst)
   {
       psf+=src;
       System.out.println(psf+" @ "+wsf);
       return;
   }
vis[src] = true;
for(Edge e : graph[src] )
{
     int nbr = e.nbr;
     int wt = e.wt;
     if(vis[nbr] == false)
     {
        printAllPath(graph,vis,nbr,dst,psf+src,wsf+wt);
     }

}
vis[src] = false;
}


// public static ArrayList<Integer> gcc2(ArrayList<Edge>[] graph,int src, boolean[] vis)
// {
//     //very complicated solution doesnot works, beacuse = mean its getting overriden
//     //so addall lagana hi hoga har bar
//       ArrayList<Integer> res = null;
//        vis[src] = true;
//        for(Edge e : graph[src])
//        {
//         int nbr = e.nbr;
//         if(vis[nbr] == false)
//         {
//             res = gcc2(graph,nbr,vis);
//         }
//        }
//        if(res == null)
//        {
//         res = new ArrayList<>();
//        }
//        res.add(src
//        );
//        return res;
// }
//dusra solution hai return type ke lia ki arraylist banalo aur usme bad mai arrlist se lekar add kar lo
//par wo bhi o(n2) hi hai
//humaesh linear recursion mai hi hum return type de skte hai ,graphs ,trees wagers pr nahi

public static void getConnectedComponent(ArrayList<Edge>[] graph,boolean[] vis,int src,ArrayList<Integer> comp)
{
     vis[src] = true;
     comp.add(src);
     for(Edge e : graph[src])
     {
       int nbr = e.nbr;
       if(vis[nbr] == false)
       {
        getConnectedComponent(graph,vis,nbr,comp);
       }
     }

}

public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph,int src)
{
      int n = graph.length;
      boolean[] vis = new boolean[n];
      ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
      for(int v=0;v<n;v++)
      {
         if(vis[v] == false)
         {
               ArrayList<Integer> comp = new ArrayList<>();
               getConnectedComponent(graph,vis,v,comp);

              //ArrayList<Integer> rres = gcc2(graph,v,vis);
               comps.add(comp);
         }
      }

System.out.println(comps);
return comps;


}

public static void gcc(ArrayList<Edge>[] graph,boolean[] vis,int src)
{
        vis[src] = true;
        for(Edge e : graph[src])
        {
              int nbr = e.nbr;
              if(vis[nbr] == false)
              {
                   gcc(graph,vis,nbr);
              }
        }
}

public static boolean isGraphConnected(ArrayList<Edge>[] graph)
{
   int n = graph.length;
   boolean[] vis = new boolean[n];
   int count =0;
   for(int v=0;v<graph.length;v++)
   {
       if(vis[v] == false)
       {
           count++;
           if(count > 1)
           {
              return false;
           }
           gcc(graph,vis,v);
       }
   }

   return true;
}
static int[] xdir ={-1,0,1,0};
static int[] ydir = {0,-1,0,1};
public static void gccIs(int[][] arr,int x, int y)
{
   arr[x][y] = -1;
   for(int d=0;d<4;d++)
   {
    int r = x + xdir[d];
    int c =  y + ydir[d];
    
    if(r>=0 && c >=0 && r<arr.length && c<arr[0].length && arr[r][c]==0)
    {
       gccIs(arr,r,c);
    }
   }

}

public static int NumOfIsland(int[][] arr)
{
    int count=0;
    for(int i=0;i<arr.length;i++)
    {
        for(int j=0;j<arr[i].length;j++)
        {
              if(arr[i][j]==0)
              {
                 count++;
                 gccIs(arr,i,j);

              }
        }
    }


    return count;   
}
public static void Hamilton(ArrayList<Edge>[] graph,HashSet<Integer> vis,int src,int orsc,String psf)
{
    if(vis.size() == graph.length-1)
    {
        psf += src;
        System.out.print(psf);
        boolean isCyclic = false;
        for(Edge e : graph[orsc])
        {
          int nbr = e.nbr;
          if(nbr == src)
          {
               isCyclic = true;
               break;
          }
        }
       if(isCyclic == true)
       {
           System.out.println("*");
       }
       else{
        System.out.println(".");
       }

    }
vis.add(src);
for(Edge e : graph[src])
{
   int nbr = e.nbr;
   if(vis.contains(nbr) == false)
   {
    Hamilton(graph,vis,nbr,orsc,psf+src);
   }

}
vis.remove(src);

}
public static class BFSPair
{
    int vtx;
    String psf;
    BFSPair(int v,String p)
    {
        this.vtx = v;
        this.psf = p;
    }

}

public static void Bfs(ArrayList<Edge>[] graph,int src)
{
    Queue<BFSPair> qu = new LinkedList();
    qu.add(new BFSPair(src,src + ""));
    boolean[] vis = new boolean[graph.length];
    while(qu.size()>0)
    {
    //get + remove it from queue
     BFSPair rem = qu.remove();
     //then mark it on the boolean array 
     if(vis[rem.vtx]== false)
     {
           vis[rem.vtx] = true;
     }
     else
     {
        continue;
     }
    System.out.println(rem.vtx+"@"+rem.psf);
    for(Edge e : graph[rem.vtx])
    {
         if(vis[e.nbr] == false)
         {
              qu.add(new BFSPair(e.nbr,rem.psf+e.nbr));
         }
    }

    }

}
public static boolean bfscyclic(ArrayList<Edge>[] graph,boolean[] vis,int src)
{
    
    Queue<Integer> qu = new LinkedList();
    qu.add(src);
    
    while(qu.size()>0)
    {
    //get + remove it from queue
    int rem = qu.remove();
     //then mark it on the boolean array 
     if(vis[rem]== false)
     {
           vis[rem] = true;
    }
     else
     {
        return true;
    }
    
    for(Edge e : graph[rem])
    {
         int nbr = e.nbr;
         if(vis[nbr] == false)
         {
              qu.add(nbr);
         }
    }

   }

    return false;
}
public static boolean isCyclic(ArrayList<Edge>[] graph)
{
    boolean[] vis = new boolean[graph.length];
    for(int v =0;v<graph.length;v++)
    { 
        if(vis[v]==false)
        {
               //boolean res = bfscyclic(graph,vis,v);
               boolean res = dfscyclic(graph, vis, v, -1);
               if(res == true)
               {
                  return true;
               }
        }
    }

    return false;
}
//iscyclic using DFS
public static boolean dfscyclic(ArrayList<Edge>graph[],boolean[] vis,int src,int par)
{
    vis[src]= true;
    for(Edge e : graph[src])
    {
    int nbr = e.nbr;
      if(vis[nbr] == true && nbr!=par) 
        {
            return true;
        }
        
        if(vis[nbr] == false)
              {  
                boolean res = dfscyclic(graph, vis, nbr, src);
                if(res==true) return true;
              }
        
    }
    return false;
}


public  static class BPair
{
   int vtx , level;

  public BPair(int v ,int l)
  {
      this.vtx = v;
      this.level = l;
  }


}
public static boolean isBipartitecomp(ArrayList<Edge>[] graph,int[] vis,int src)
{
    Queue<BPair> qu = new LinkedList<>();
    qu.add(new BPair(src, 1));
    while(qu.size()>0)
    {
        //remove
        BPair rem = qu.remove();
       //check first
      if(vis[rem.vtx]!=-1)
      {
        if(vis[rem.vtx] == rem.level)
         {
            continue;}
         else
        {
            return false;
        }
     }
    // then mark
     vis[rem.vtx] = rem.level;
     for(Edge e: graph[rem.vtx])
        {
           int nbr = e.nbr;
           if(vis[nbr] == -1)
           {
               qu.add(new BPair(nbr,rem.level+1));
           }
        } 
    }
    

return true;

}
public static boolean isbipartite(ArrayList<Edge>[] graph)
{
       
       int[] vis = new int[graph.length];
       Arrays.fill(vis,-1);
       for(int v=0;v<vis.length;v++)
       {
        if(vis[v]==-1)
        {
           boolean res =  isBipartitecomp(graph,vis,v);
           if(res == false ) return false;
        }

       }

return true;
}
public static int spreadOfInfection(ArrayList<Edge>[] graph,int[] vis,int src,int time)
{
   int count=0;
   Queue<BPair> qu = new LinkedList<>();
   qu.add(new BPair(src,1));
   while(qu.size()>0)
   {
     BPair rem = qu.remove();
     if(vis[rem.vtx]!=0)
     {
       continue;
     }
     vis[rem.vtx] = rem.level;
     
     if(rem.level>time)
     {
        break;
     }
     count++;
     System.out.println(rem.vtx+"  Infected at "+rem.level);
     for(Edge e: graph[rem.vtx])
     {
         int nbr = e.nbr;
         if(vis[nbr] == 0)
         {
            qu.add(new BPair(nbr,rem.level+1));
         }
     }

   }

    return count;
}

public static class DPair implements Comparable<DPair>
{
   int vtx;
   String psf;
   int wsf;
    DPair(int v,String p,int w)
   {
       this.vtx = v;
       this.psf = p;
       this.wsf = w;
       
   }
    
    public int compareTo(DPair o)
    {
        return this.wsf - o.wsf;
    }
}
public static void dijkstras(ArrayList<Edge>[] graph,int src)
{
    //Dijkstras algorithm is finding shortest path from src to dstn with minimum weight, based on WEIGHT not on edges.
    //can work only on positive weights, not on negative weights
    //same algo based on BFS only with priority Queue that is min PQ
PriorityQueue<DPair> qu = new PriorityQueue<>();
qu.add(new DPair(src," "+src,0));
boolean[] vis = new boolean[graph.length];
    while(qu.size()>0)
    {
         //remove + get
        DPair rem = qu.remove();
        //check
        if(vis[rem.vtx] == true)
            {
                continue;
            }
            //mark
            vis[rem.vtx] = true;
            //print path
            System.out.println(rem.vtx +" via "+ rem.psf+"  @  "+ rem.wsf);

            //add nbr
            for(Edge e : graph[rem.vtx])
            {
                int nbr = e.nbr;
                if(vis[nbr] == false)
                {
                  qu.add(new DPair(nbr,rem.psf+nbr,rem.wsf+e.wt));
                }

            }

    }


}

public static class PHelper implements Comparable<PHelper>
{
int vtx, parent;
int wt;
public PHelper(int v, int p,int w)
{
  this.vtx = v;
  this.parent = p;
  this.wt = w;

}
public  int compareTo(PHelper o)
{
   return this.wt - o.wt;

}
}
public static void primsAlgo(ArrayList<Edge>[] graph)
{
//Understand the difference in PRims and Dijsktras by dry running the solutions.
//Prims travels covering all the vertices.

    PriorityQueue<PHelper>pq = new PriorityQueue<>();
pq.add(new PHelper(0,-1,0));
boolean[] vis = new boolean[graph.length];
ArrayList<Edge>[] mst = new ArrayList[graph.length];
for(int i=0;i<graph.length;i++)
    {
        mst[i] = new ArrayList<>();
    }
while(pq.size()>0)
    {
      PHelper rem = pq.remove();
      if(vis[rem.vtx]==true){continue;}
      vis[rem.vtx] = true;
      if(rem.parent !=-1)
      {
              System.out.println(" [ "+rem.vtx+" - "+rem.parent+" @ "+rem.wt+" ] ");  
              addEdge(mst, rem.vtx, rem.parent, rem.wt);  
      }

      for(Edge e : graph[rem.vtx])
      {
         int nbr = e.nbr;
         if(vis[nbr] == false)
         {
                pq.add(new PHelper(e.nbr,rem.vtx,e.wt));
         }
      }

    

    }
    display(mst);

}
public static void topologicalSort(ArrayList<Edge>[] graph,boolean[] vis,int src,Stack<Integer>st)
{
    //in this question, this is topological sort which is basically the opposite of order of compilation
    //here it matters why we are not printing in pre order and we are printing in post order
    //Topolical sort : permutation so that if uv, u should appear first and then v
    //if one component it will work ,,if multiple component it will not work,try dry run.
vis[src] = true;

for(Edge e: graph[src])
{
    int nbr = e.nbr;
    if(vis[nbr]== false)
    {
        topologicalSort(graph,vis,nbr,st);
    }
}
st.push(src);
}
public static void TShelper(ArrayList<Edge>[] graph)
{
    boolean[] vis = new boolean[graph.length];
    Stack<Integer> st = new Stack<>();
   for(int v=0;v<graph.length;v++)
   {
        if(vis[v]==false)
        {
            topologicalSort(graph,vis,v,st);
        }
   }
while(st.size()>0)
{
   System.out.println(st.pop());
}



}



//*******************************************GRAHPH**************************************************** */
public static ArrayList<Edge>[] createGraph()
{
    int n = 7;
    ArrayList<Edge>[] graph = new ArrayList[n];

    for(int i=0;i<n;i++)
    {
       graph[i] = new ArrayList<>();
    }
    //another way is they can give you a 2D array
    // int[][] data  = {
    //     {0,1,10},
    //     {0,3,40},
    //     {1,2,10},
    //     {2,3,10},
    //     {3,4,2},
    //     {4,5,3},
    //     {4,6,8},
    //     {5,6,3} };
    //one way
    // addEdge(graph,0,1,10);
    // addEdge(graph,0,2,10);
    // addEdge(graph,1,2,10);
    addEdge(graph,0,1,10);
    addEdge(graph,0,3,25);
    addEdge(graph,1,2,10);
    addEdge(graph,2,3,10);
   //addEdge(graph,2,5,10);
    addEdge(graph,4,3,2);
    addEdge(graph,4,5,3);
    addEdge(graph,4,6,8);
    addEdge(graph,5,6,3);
    

    //  for(int[] arr : data)
    //  {
    //     addEdge(graph, arr[0],arr[1], arr[2]);
    //  }

  
   return graph;
   
}
public static void fun()
{

    //  int[][] arr ={
    //     {0,1,1,0,0},
    //     {0,0,1,0,1},
    //     {1,0,1,1,1},
    //     {1,1,1,0,1},
    //     {1,0,0,0,1},
    //     {0,1,1,1,1}
    //  };
    // System.out.println(NumOfIsland(arr));
 //int n = 7;
 ArrayList<Edge>[] graph = createGraph();
 TShelper(graph);
 //primsAlgo(graph);
 //dijkstras(graph,0);
 //int[] vis = new int[graph.length];
 //Arrays.fill(vis,0);
 //int c =  spreadOfInfection(graph,vis,6,3);
 //System.out.println(c);



 //boolean res = isbipartite(graph);
 //System.out.println(res);


 //boolean res = isCyclic(graph);
 //System.out.println(res);
 //Bfs(graph,0);
 //HashSet<Integer> vis = new HashSet<>();
 //Hamilton(graph, vis, 5, 5, "");

//    boolean[] vis = new boolean[8];
//    System.out.println(isGraphConnected(graph));
  // getConnectedComponents(graph,0);

   //System.out.println(hasPath(graph,0,6,vis));
   //display(graph);
   //printAllPath(graph,vis,0,6,"",0);
   //getConnectedComponents(graph, n);
}
public static void addEdge(ArrayList<Edge> [] graph,int src,int nbr,int wt)
{
    graph[src].add(new Edge(src,nbr,wt));
   // graph [nbr].add(new Edge (nbr,src,wt));

}

public static void main(String[] args)
{
    fun();
   
}
}

