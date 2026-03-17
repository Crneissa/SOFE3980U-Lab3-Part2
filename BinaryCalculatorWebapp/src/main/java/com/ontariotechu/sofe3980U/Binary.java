package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'

	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0";
			return;
		}

		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0";
				return;
			}
		}

		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		if (this.number.isEmpty()) {
			this.number = "0";
		}
	}

	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}

	/**
	* Adding two binary variables.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of num1+num2.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		int carry=0;
		String num3="";

		while(ind1>=0 ||  ind2>=0 || carry!=0)
		{
			int sum=carry;

			if(ind1>=0){
				sum += (num1.number.charAt(ind1)=='1')? 1:0;
				ind1--;
			}
			if(ind2>=0){
				sum += (num2.number.charAt(ind2)=='1')? 1:0;
				ind2--;
			}

			carry=sum/2;
			sum=sum%2;
			num3 =( (sum==0)? "0":"1")+num3;
		}

		return new Binary(num3);
	}

	/**
	* Multiplying two binary variables.
	*
	* @param num1 The first operand object
	* @param num2 The second operand object
	* @return A binary variable with a value of num1*num2.
	*/
	public static Binary multiply(Binary num1, Binary num2)
	{
		int a = Integer.parseInt(num1.number, 2);
		int b = Integer.parseInt(num2.number, 2);
		int result = a * b;
		return new Binary(Integer.toBinaryString(result));
	}

	/**
	* Bitwise AND operation between two binary variables.
	*
	* @param num1 The first operand object
	* @param num2 The second operand object
	* @return A binary variable with a value of num1 & num2.
	*/
	public static Binary and(Binary num1, Binary num2)
	{
		int maxLen = Math.max(num1.number.length(), num2.number.length());

		String a = String.format("%" + maxLen + "s", num1.number).replace(' ', '0');
		String b = String.format("%" + maxLen + "s", num2.number).replace(' ', '0');

		String result = "";

		for (int i = 0; i < maxLen; i++) {
			result += (a.charAt(i) == '1' && b.charAt(i) == '1') ? "1" : "0";
		}

		return new Binary(result);
	}

	/**
	* Bitwise OR operation between two binary variables.
	*
	* @param num1 The first operand object
	* @param num2 The second operand object
	* @return A binary variable with a value of num1 | num2.
	*/
	public static Binary or(Binary num1, Binary num2)
	{
		int maxLen = Math.max(num1.number.length(), num2.number.length());

		String a = String.format("%" + maxLen + "s", num1.number).replace(' ', '0');
		String b = String.format("%" + maxLen + "s", num2.number).replace(' ', '0');

		String result = "";

		for (int i = 0; i < maxLen; i++) {
			result += (a.charAt(i) == '1' || b.charAt(i) == '1') ? "1" : "0";
		}

		return new Binary(result);
	}
}