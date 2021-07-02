package SDET;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Details{
	String Brand;
	String Model;
	int Price;
	
	public Details(String brand, String model, int price) {
		Brand=brand;
		Model=model;
		this.Price=price;
	}
}

class sortByPrice implements Comparator<Details>{

	@Override
	public int compare(Details o1, Details o2) {
		// TODO Auto-generated method stub
		return o1.Price - o2.Price;
	}
	
}
public class Solution {
	
	public static ArrayList<Details> organize(String str){
		
		int len=str.length();
		int index=0;
		int i=index;
		ArrayList<String> brand=new ArrayList();
		ArrayList<String> model=new ArrayList();
		ArrayList<Integer> price=new ArrayList();
		ArrayList<Details> data=new ArrayList();
		
		int startIndex=0;
		while(index<len) {
			i=index;
		    if(str.charAt(index)=='\'' ){
		      i=index;
		      if(i-1-startIndex+1!=0) {
		      brand.add(str.substring(startIndex,i-1));
		      }
		      String currentModel="";
		      do{
		          
		          currentModel=currentModel+str.charAt(i);
		          i++;
		        }while(i<len && str.charAt(i)!='\'');
		          currentModel=currentModel+str.charAt(i);
		          startIndex=i+2;
		          i+=2;
		          while(i<len && str.charAt(i)!=','){
		            i++;
		          }
		          price.add(Integer.parseInt(str.substring(startIndex,i).trim()));
		          i++;
		          startIndex=i;
		          
		          model.add(currentModel);
		          
		          data.add(new Details(brand.get(brand.size()-1),model.get(brand.size()-1),price.get(brand.size()-1)));
		    }
		    index=i+1;
		    
		}		
		Collections.sort(data, new sortByPrice());
		return data;
	}
	
	public static void Print(ArrayList<Details> List) {
		for(Details i : List) {
			System.out.println(i.Brand+","+i.Model+","+i.Price);
		}
	}
	
	public static void main(String[] args) throws IOException {

		String content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Arun\\Downloads\\TestCase.txt")));
		
		ArrayList<Details> List =organize(content);
		Print(List);
	}

}
