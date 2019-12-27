import java.util.*;
import java.io.*;
public class MovieGraph {
	
		// the GraphType is String because both movies and cast are all strings
		
		GraphType<String> movieGraph;
		HashSet<String> movies;
		HashSet<String> cast;
		String fileName;
	
		
		public MovieGraph() {
			
			 movieGraph = new GraphType<>();
			 cast = new HashSet<>();
			 movies = new HashSet<>();
			 fileName="movies.txt";
			 
		}
		
		public void numMovies() {
			System.out.println(movies.size());
		}
		
		public void numCast() {
			System.out.println(cast.size());
		}
		
		public void moviesList(String cm) {
			System.out.println (movieGraph.graph.get(cm));
		}
		
		public ArrayList <String> commonMovies(ArrayList<String> al1, ArrayList<String> al2) {
			ArrayList <String> common = new ArrayList<String>();
			for (String s: al1)
				if (al2.contains(s))
					common.add(s);
			return common;
		}
		
		public void coCast (int minMovies) {
			ArrayList<String> castMin = new ArrayList<>();
			for (String s: cast)
				if (movieGraph.graph.get(s).size() >= minMovies)
					castMin.add(s); 
			for (String p: castMin)
					for (String r: castMin) {
						if (p.compareTo(r)<0) {
							// intersect the arrayList of movies featuring p and r
							ArrayList<String> al = commonMovies(movieGraph.graph.get(p), movieGraph.graph.get(r));
							if (al.size() >= minMovies) {
								System.out.println( "Cast members are " + p + " and " + r);
								System.out.println(al);
								System.out.println();
							}
					}
				}
			
		}
		
	
		public void readMovieInfo () {
			Scanner fileScanner;
			String line; 
			  try
              {
                  fileScanner = new Scanner (new File (fileName));
                  while (fileScanner.hasNext()) {
                   line = fileScanner.nextLine();
                   String[] movieInfo = line.split("/");
                   String movieName = movieInfo[0];
                   movies.add(movieName);
                   movieGraph.addVertex(movieName);
                   for (int i=1; i<movieInfo.length;i++) {
                	   String cm = movieInfo[i];
                	   
                	   if (!cast.contains(cm)) {
                	   movieGraph.addVertex(cm);
                	   cast.add(cm);
                	   
                	   }
                	   movieGraph.addEdge(cm, movieName);
                	   
                	   
                   }
          
                   

                   }
		}
			  catch (IOException e)
              {
                  System.out.println(e);
              }
			
		}
		public static void main (String [] args) {
			MovieGraph mg = new MovieGraph();
			mg.readMovieInfo();
			mg.moviesList("Keaton, Michael");
			
			mg.coCast(15); 
		
		}
}
