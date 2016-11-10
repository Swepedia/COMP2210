class Test implements Comparable<Test>
{

   private int hold;
   private int something;
   
   public Test(int A, int B)
   {
      hold = A;
      something = B;
   }
   
   
   public int compareTo(Test that)
   {
      if(this.hold < that.hold)
      {
         return -1;
      }
      else if (this.hold > that.hold)
      {
         return 1;
      }
      else 
         return 0;
   }
}