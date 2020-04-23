 using System;
using System.Collections.Generic;
using System.Text;
//the namespace must be PAT.Lib, the class and method names can be arbitrary
namespace PAT.Lib
{
    /// <summary>
    /// The math library that can be used in your model.
    /// all methods should be declared as public static.
    /// 
    /// The parameters must be of type "int", or "int array"
    /// The number of parameters can be 0 or many
    /// 
    /// The return type can be bool, int or int[] only.
    /// 
    /// The method name will be used directly in your model.
    /// e.g. call(max, 10, 2), call(dominate, 3, 2), call(amax, [1,3,5]),
    /// 
    /// Note: method names are case sensetive
    /// </summary>
    public class NewLib
    {
	       public static bool isDuplicated(int[] values)
        {
		          for(int i=0;i<values.Length;i++){
		          	for(int j=i+1;j<values.Length;j++){
		    	      	if(values[i]==values[j]){
		        	  		return true;
		          		}
		          	}
		          }
		    for(int i=0;i<values.Length;i++){
		    	Console.WriteLine(values[i]);
		    }
        	return false;
        }
        
        public static void insertValue(int[] values, int value, int index){
        	if(index>=0&&index<=values.Length){
        		values[index]=value;
        	}
        }
    }
}
