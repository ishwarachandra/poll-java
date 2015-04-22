package testPoll;

public class ArrayCompararision {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arrayVal []={1,5,4,3,6,7};
		int arrayVal2 []={4,3};
		for(int i=0;i<arrayVal.length;i++)
		{
			for(int j=0;j<arrayVal2.length;j++)
			{
				if(arrayVal[i]==arrayVal2[j])
				{
					System.out.println(arrayVal[i]+"InsideTrue");
				}
				else
				{
					System.out.println(arrayVal[i]+"Insidefalserue");
				}
			}
		}
		
	}

}
