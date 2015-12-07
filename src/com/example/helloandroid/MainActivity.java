package com.example.helloandroid;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

	private Button[] numButton = new Button[11];// 数字按钮
	private Button[] commandButton = new Button[6];// 运算符按钮
	private Button clear;
	private EditText editText;
	private String lastCommand;// 最后的字符
	private boolean firstFlag;// 是否是第一次输入
	private boolean clearFlag;// 是否需要清空
	private double result;

	public MainActivity() {// 初始化各项值
		lastCommand = "=";
		firstFlag = true;
		clearFlag = false;
		result = 0;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取运算符
		commandButton[0]=(Button)findViewById(R.id.button12);
		commandButton[1]=(Button)findViewById(R.id.button13);
		commandButton[2]=(Button)findViewById(R.id.button14);
		commandButton[3]=(Button)findViewById(R.id.button15);
		commandButton[4]=(Button)findViewById(R.id.button16);
		commandButton[5]=(Button)findViewById(R.id.button18);
		//获取数字按钮
		numButton[0]=(Button)findViewById(R.id.button1);
		numButton[1]=(Button)findViewById(R.id.button2);
		numButton[2]=(Button)findViewById(R.id.button3);
		numButton[3]=(Button)findViewById(R.id.button4);
		numButton[4]=(Button)findViewById(R.id.button5);
		numButton[5]=(Button)findViewById(R.id.button6);
		numButton[6]=(Button)findViewById(R.id.button7);
		numButton[7]=(Button)findViewById(R.id.button8);
		numButton[8]=(Button)findViewById(R.id.button9);
		numButton[9]=(Button)findViewById(R.id.button10);
		numButton[10]=(Button)findViewById(R.id.button11);
		System.out.println("计算器");		
		//实例化显示屏
		editText=(EditText) findViewById(R.id.editText);
		editText.setText("0.0");
		class NumberAction implements OnClickListener{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button btn=(Button) v;
				String input=btn.getText().toString();
				if(firstFlag){//是第一次输入
					if(input.equals(".")){
						return;
					}if(input.equals("0.0")){
						editText.setText("");
					}
					firstFlag=false;
				}
				else{
					String editTextStr = editText.getText().toString();
					// 判断显示区域的值里面是否已经有".",如果有,输入的又是".",就什么都不做 
					if (editTextStr.indexOf(".") != -1 && input.equals(".")) { 
	                    return; 
	                } 
	                // 判断显示区域的值里面只有"-",输入的又是".",就什么都不做  
	                if (editText.equals("-") && input.equals(".")) { 
	                    return; 
	                } 
	                // 判断显示区域的值如果是"0",输入的不是".",就什么也不做  
	                if (editText.equals("0") && !input.equals(".")) { 
	                    return; 
	                } 
				}
				 if (clearFlag) { 
		                editText.setText(""); 
		                clearFlag = false;// 还原初始值,不需要清空  
		            } 
		            editText.setText(editText.getText().toString() + input);// 设置显示区域的值  
		        } 
			}
		class CommandAction implements OnClickListener{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button btn=(Button) v;
				String inputCommand=btn.getText().toString();
				if(firstFlag){
					if(inputCommand.equals("-")){
						editText.setText("-");
						firstFlag=false;
					}
				}else{
					if (!clearFlag) {// 如果flag=false不需要清空显示区的值,就调用方法计算  
						calculator1(Double.parseDouble(editText.getText().toString()));// 保存显示区域的值,并计算  
					} 	
				}
	                // 保存你点击的运算符  
	                lastCommand = inputCommand; 
	                clearFlag = true;// 因为我这里已经输入过运算符,  
	            } 
			}
		//实例化监听器对象
		NumberAction na=new NumberAction();
		CommandAction ca=new CommandAction();
		for(Button bc:numButton){
			bc.setOnClickListener(na);
		}
		for(Button bc:commandButton){
			bc.setOnClickListener(ca);
		}
		
		//clear按鈕的監聽
		clear=(Button)findViewById(R.id.button17);
		clear.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lastCommand = "=";
				firstFlag = true;
				clearFlag = false;
				result = 0;
				editText.setText("0.0");
			}
		});
		}
//计算方法
	private void calculator1(double x){
		 
	    if(lastCommand.equals("+")){
			 result+=x;
		 }
		 else if(lastCommand.equals("-")){
			 result-=x;
		 }
		 else if(lastCommand.equals("/")){
			 result/=x;
		 } else if(lastCommand.equals("*")){
			 result*=x;
		 } else if(lastCommand.equals("=")){
			 result=x;
		 }
		 editText.setText(""+result);
	}


}
