package com.example.helloandroid;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

	private Button[] numButton = new Button[11];// ���ְ�ť
	private Button[] commandButton = new Button[6];// �������ť
	private Button clear;
	private EditText editText;
	private String lastCommand;// �����ַ�
	private boolean firstFlag;// �Ƿ��ǵ�һ������
	private boolean clearFlag;// �Ƿ���Ҫ���
	private double result;

	public MainActivity() {// ��ʼ������ֵ
		lastCommand = "=";
		firstFlag = true;
		clearFlag = false;
		result = 0;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ȡ�����
		commandButton[0]=(Button)findViewById(R.id.button12);
		commandButton[1]=(Button)findViewById(R.id.button13);
		commandButton[2]=(Button)findViewById(R.id.button14);
		commandButton[3]=(Button)findViewById(R.id.button15);
		commandButton[4]=(Button)findViewById(R.id.button16);
		commandButton[5]=(Button)findViewById(R.id.button18);
		//��ȡ���ְ�ť
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
		System.out.println("������");		
		//ʵ������ʾ��
		editText=(EditText) findViewById(R.id.editText);
		editText.setText("0.0");
		class NumberAction implements OnClickListener{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Button btn=(Button) v;
				String input=btn.getText().toString();
				if(firstFlag){//�ǵ�һ������
					if(input.equals(".")){
						return;
					}if(input.equals("0.0")){
						editText.setText("");
					}
					firstFlag=false;
				}
				else{
					String editTextStr = editText.getText().toString();
					// �ж���ʾ�����ֵ�����Ƿ��Ѿ���".",�����,���������".",��ʲô������ 
					if (editTextStr.indexOf(".") != -1 && input.equals(".")) { 
	                    return; 
	                } 
	                // �ж���ʾ�����ֵ����ֻ��"-",���������".",��ʲô������  
	                if (editText.equals("-") && input.equals(".")) { 
	                    return; 
	                } 
	                // �ж���ʾ�����ֵ�����"0",����Ĳ���".",��ʲôҲ����  
	                if (editText.equals("0") && !input.equals(".")) { 
	                    return; 
	                } 
				}
				 if (clearFlag) { 
		                editText.setText(""); 
		                clearFlag = false;// ��ԭ��ʼֵ,����Ҫ���  
		            } 
		            editText.setText(editText.getText().toString() + input);// ������ʾ�����ֵ  
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
					if (!clearFlag) {// ���flag=false����Ҫ�����ʾ����ֵ,�͵��÷�������  
						calculator1(Double.parseDouble(editText.getText().toString()));// ������ʾ�����ֵ,������  
					} 	
				}
	                // ���������������  
	                lastCommand = inputCommand; 
	                clearFlag = true;// ��Ϊ�������Ѿ�����������,  
	            } 
			}
		//ʵ��������������
		NumberAction na=new NumberAction();
		CommandAction ca=new CommandAction();
		for(Button bc:numButton){
			bc.setOnClickListener(na);
		}
		for(Button bc:commandButton){
			bc.setOnClickListener(ca);
		}
		
		//clear���o�ıO 
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
//���㷽��
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
