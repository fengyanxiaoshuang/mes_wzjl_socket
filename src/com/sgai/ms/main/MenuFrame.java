package com.sgai.ms.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sgai.ms.jdbc.DefaultDataSource;
import com.sgai.ms.jdbc.IDataSource;

/** 主菜单界面 */
public class MenuFrame extends JFrame {
	
	private JTextField oracleIpField,oracleNameField,userField,portField,scaleIpField,timeField,tempDiffField,scaleDiffField,port2Field,message1Field,message2Field,scaleWeight;
	
	private JPasswordField passWordField;
	
	private JTextArea tempWeight;
	
	private JButton start,end,weight,save;
	
	private JComboBox comboBox,ipBox;
	
	private Map<String,String> map,mapMat;
	
	private boolean flag = true;
	
	private boolean enFlag = true;

	public MenuFrame() {
		setTitle("成品秤接口软件");
		setSize(575, 600);
		setContentPane(createContentPane());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				clientContext.exit(MenuFrame.this);
			}
		});
	}

	private JPanel createContentPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.NORTH, createSocketPane());
		pane.add(BorderLayout.CENTER, createWeightPane());
		return pane;
	}
	
	private JPanel createSocketPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.setBorder(BorderFactory.createTitledBorder("配置"));
		pane.add(BorderLayout.NORTH, createOraclePane());
		pane.add(BorderLayout.CENTER, createYbPortPane());
		pane.add(BorderLayout.SOUTH, createButtonPane());
		return pane;
	}
	
	private JPanel createOraclePane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.NORTH, createOracleIpNamePane());
		pane.add(BorderLayout.CENTER, createOracleUserPassWordPane());
		pane.add(BorderLayout.SOUTH, createScaleIpPane());
		return pane;
	}
	
	private JPanel createOracleIpNamePane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.WEST, createOracleIpPane());
		pane.add(BorderLayout.CENTER, createOracleNamePane());
		return pane;
	}
	
	private JPanel createOracleIpPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel oracleIpLabel = new JLabel("数据库IP：");
		oracleIpLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		oracleIpField = new JTextField(18);
		oracleIpField.setText("10.88.252.88:1521");
		oracleIpField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		oracleIpField.setEnabled(false);
		oracleIpField.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2 && enFlag) {// 鼠标双击
					 oracleIpField.setEnabled(true);
				 }
			 }
		});
		pane.add(oracleIpLabel);
		pane.add(oracleIpField);
		return pane;
	}
	
	private JPanel createOracleNamePane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel oracleNameLabel = new JLabel("数据库名称：");
		oracleNameLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		oracleNameField = new JTextField(10);
		oracleNameField.setText("sgjttlq");
		oracleNameField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		oracleNameField.setEnabled(false);
		oracleNameField.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2 && enFlag) {// 鼠标双击
					 oracleNameField.setEnabled(true);
				 }
			 }
		});
		pane.add(oracleNameLabel);
		pane.add(oracleNameField);
		return pane;
	}
	
	private JPanel createOracleUserPassWordPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.WEST, createUserPane());
		pane.add(BorderLayout.CENTER, createPassWordPane());
		return pane;
	}
	
	private JPanel createUserPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel userLabel = new JLabel("用 户 名：");
		userLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		userField = new JTextField(10);
		userField.setText("sgjtmes");
		userField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		userField.setEnabled(false);
		userField.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2 && enFlag) {// 鼠标双击
					 userField.setEnabled(true);
				 }
			 }
		});
		pane.add(userLabel);
		pane.add(userField);
		return pane;
	}
	
	private JPanel createPassWordPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel passWordLabel = new JLabel("密      码：");
		passWordLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		passWordField = new JPasswordField(10);
		passWordField.setText("sgjtmes");
		passWordField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		passWordField.setEnabled(false);
		passWordField.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2 && enFlag) {// 鼠标双击
					 passWordField.setEnabled(true);
				 }
			 }
		});
		pane.add(passWordLabel);
		pane.add(passWordField);
		return pane;
	}
	
	private JPanel createScaleIpPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ScaleNameLabel = new JLabel("成品秤名称：");
		ScaleNameLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		IDataSource dataSource = null;
		dataSource = new DefaultDataSource(oracleIpField.getText(),oracleNameField.getText(),userField.getText(),passWordField.getText());
		String sql = "SELECT METER_DESC,SCALE_IP from MES_MS_SCALE_CONFIG";
		try {
			mapMat = dataSource.queryMapMat(sql);
			Vector<String> ss=new Vector<String>();
			ss.addAll(mapMat.keySet());
			ipBox=new JComboBox(ss);
			ipBox.setSize(10, 10);
		}catch (Exception e) {
			// TODO: handle exception
		}
		ipBox.setPreferredSize(new Dimension(150, 30));// 这里就是设置JComboBox宽度的代码
		pane.add(ScaleNameLabel);
		pane.add(ipBox);
		return pane;
	}
	
	private JPanel createYbPortPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.NORTH, createIpPortPane());
		pane.add(BorderLayout.CENTER, createTimePort2Pane());
		pane.add(BorderLayout.SOUTH, createMessagePane());
		return pane;
	}
	
	private JPanel createIpPortPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.WEST, createIpPane());
		pane.add(BorderLayout.EAST, createPortPane());
		return pane;
	}
	
	private JPanel createIpPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ipLabel = new JLabel("仪 表 IP：");
		ipLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		scaleIpField = new JTextField(18);
		scaleIpField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		scaleIpField.setEnabled(false);
		scaleIpField.setText(mapMat.get(ipBox.getSelectedItem()));
		scaleIpField.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e) {
				 if (e.getClickCount() == 2 && enFlag) {// 鼠标双击
					 scaleIpField.setEnabled(true);
				 }
			 }
		});
		pane.add(ipLabel);
		pane.add(scaleIpField);
		return pane;
	}
	
	private JPanel createPortPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel portLabel = new JLabel("仪表端口 1：");
		portLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		portField = new JTextField(10);
		portField.setText("10000");
		portField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		pane.add(portLabel);
		pane.add(portField);
		return pane;
	}
	
	private JPanel createTimePort2Pane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.WEST, createTimePane());
		pane.add(BorderLayout.EAST, createPort2Pane());
		return pane;
	}
	
	private JPanel createTimePane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel timeLabel = new JLabel("间隔时间：");
		timeLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		timeField = new JTextField(10);
		timeField.setText("10");
		timeField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		pane.add(timeLabel);
		pane.add(timeField);
		return pane;
	}
	
	private JPanel createPort2Pane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel port2Label = new JLabel("仪表端口 2：");
		port2Label.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		port2Field = new JTextField(10);
		port2Field.setText("10001");
		port2Field.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		pane.add(port2Label);
		pane.add(port2Field);
		return pane;
	}
	
	private JPanel createMessagePane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.NORTH, createMessage1Pane());
		pane.add(BorderLayout.CENTER, createMessagetPane());
		pane.add(BorderLayout.SOUTH, createDiffPane());
		return pane;
	}
	
	private JPanel createMessage1Pane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel message1Label = new JLabel("发送电文1：");
		message1Label.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		message1Field = new JTextField(42);
		message1Field.setText("00020201");
		message1Field.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		pane.add(message1Label);
		pane.add(message1Field);
		return pane;
	}
	
	private JPanel createMessagetPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel message2Label = new JLabel("发送电文2：");
		message2Label.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		message2Field = new JTextField(42);
		message2Field.setText("003401123456789012      300001        end");
		message2Field.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		pane.add(message2Label);
		pane.add(message2Field);
		return pane;
	}
	
	private JPanel createDiffPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.WEST, createTempDiffPane());
		pane.add(BorderLayout.EAST, createScaleDiffPane());
		return pane;
	}
	
	private JPanel createTempDiffPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel tempDiffLabel = new JLabel("零点监测报警差值：");
		tempDiffLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		tempDiffField = new JTextField(5);
		tempDiffField.setText("600");
		tempDiffField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		pane.add(tempDiffLabel);
		pane.add(tempDiffField);
		return pane;
	}
	
	private JPanel createScaleDiffPane() {
		JPanel pane = new JPanel(new FlowLayout());
		pane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel scaleDiffLabel = new JLabel("标准卷报警差值：");
		scaleDiffLabel.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		scaleDiffField = new JTextField(5);
		scaleDiffField.setText("20");
		scaleDiffField.setFont(new Font ( "宋体" , Font.PLAIN , 20));
		pane.add(scaleDiffLabel);
		pane.add(scaleDiffField);
		return pane;
	}
	
	private JPanel createButtonPane() {
		JPanel pane = new JPanel(new FlowLayout());
		start = createImgBtn("链接");
		end = createImgBtn("断开");
		end.setEnabled(false);
		pane.add(start);
		pane.add(end);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.connect(MenuFrame.this);
			}
		});
		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// long clickTime = e.getWhen();
				clientContext.end();
			}
		});
		return pane;
	}
	
	private JPanel createWeightPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.setBorder(BorderFactory.createTitledBorder("称重信息"));
		pane.add(BorderLayout.NORTH, createTempWeightPane());
		pane.add(BorderLayout.SOUTH, createButton2Pane());
		return pane;
	}
	
	private JPanel createTempWeightPane() {
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(BorderLayout.NORTH, createTempPane());
		pane.add(BorderLayout.CENTER, createScalePane());
		pane.add(BorderLayout.SOUTH, createScaleWeightPane());
		return pane;
	}
	
	private JPanel createTempPane() {
		JPanel pane = new JPanel(new FlowLayout());
		JLabel tempLabel = new JLabel("  实时重量：");
		tempLabel.setFont(new Font ( "宋体" , Font.PLAIN , 25));
		tempWeight = new JTextArea(1, 10);
		tempWeight.setBackground(Color.BLACK);
		tempWeight.setFont(new Font ( "宋体" , Font.PLAIN , 30));
		tempWeight.setRows(1);
		tempWeight.setEditable(false);
		pane.add(tempLabel);
		pane.add(tempWeight);
		return pane;
	}
	
	private JPanel createScalePane() {
		JPanel pane = new JPanel(new FlowLayout());
		JLabel tempLabel = new JLabel("标准卷卷号：");
		tempLabel.setFont(new Font ( "宋体" , Font.PLAIN , 25));

		IDataSource dataSource = null;
		dataSource = new DefaultDataSource(oracleIpField.getText(),oracleNameField.getText(),userField.getText(),passWordField.getText());
		String sql = "SELECT mat_id,weight from  MES_MS_STANDARD_VOLUME";
		try {
			map = dataSource.queryMap(sql);
			Vector<String> ss=new Vector<String>();
			ss.addAll(map.keySet());
			comboBox=new JComboBox(ss);
			comboBox.setSize(10, 10);
			comboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
					scaleWeight.setText(map.get(comboBox.getSelectedItem()));
				}
			});
		}catch (Exception e) {
			// TODO: handle exception
		}
		comboBox.setPreferredSize(new Dimension(150, 30));// 这里就是设置JComboBox宽度的代码
		pane.add(tempLabel);
		pane.add(comboBox);
		return pane;
	}
	
	private JPanel createScaleWeightPane() {
		JPanel pane = new JPanel(new FlowLayout());
		JLabel tempLabel = new JLabel("标准卷重量：");
		tempLabel.setFont(new Font ( "宋体" , Font.PLAIN , 25));
		scaleWeight = new JTextField(11);
		scaleWeight.setText(map.get(comboBox.getSelectedItem()));
		scaleWeight.setFont(new Font ( "宋体" , Font.PLAIN , 25));
		scaleWeight.setEditable(false);
		pane.add(tempLabel);
		pane.add(scaleWeight);
		return pane;
	}
	
	private JPanel createButton2Pane() {
		JPanel pane = new JPanel(new FlowLayout());
		weight = createImgBtn("称重");
		save = createImgBtn("保存");
		weight.setEnabled(false);
		save.setEnabled(false);
		pane.add(weight);
		pane.add(save);
		weight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.weight();
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// long clickTime = e.getWhen();
				clientContext.save();
			}
		});
		return pane;
	}

	// 创建图片按钮的方法
	private JButton createImgBtn(String txt) {
		JButton button = new JButton(txt);
		// 垂直文本对齐位置
		button.setVerticalTextPosition(JButton.BOTTOM);
		// 水平文本对齐位置
		button.setHorizontalTextPosition(JButton.CENTER);
		return button;
	}

	private ClientContext clientContext;

	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	public String[] getConfig() {
		String[] oracle = new String[13];
		if(oracleIpField.getText() == null || oracleIpField.getText().equals("")){
			notNull("数据库IP不能为空！");
			return null;
		}else{
			oracle[0] = oracleIpField.getText();
		}
		if(oracleNameField.getText() == null || oracleNameField.getText().equals("")){
			notNull("数据库名称不能为空！");
			return null;
		}else{
			oracle[1] = oracleNameField.getText();
		}
		if(userField.getText() == null || userField.getText().equals("")){
			notNull("用户名不能为空！");
			return null;
		}else{
			oracle[2] = userField.getText();
		}
		if(passWordField.getText() == null || passWordField.getText().equals("")){
			notNull("密码不能为空！");
			return null;
		}else{
			oracle[3] = passWordField.getText();
		}
		if(ipBox.getSelectedItem() == null || ipBox.getSelectedItem().equals("")){
			notNull("仪表IP不能为空！");
			return null;
		}else{
			if(isIpValid(mapMat.get(ipBox.getSelectedItem()))){
				oracle[4] = mapMat.get(ipBox.getSelectedItem());
			}else{
				notNull("仪表IP格式不正确！");
				return null;
			}
		}
		if(ipBox.getSelectedItem() == null || ipBox.getSelectedItem().equals("")){
			notNull("仪表IP不能为空！");
			return null;
		}else{
			oracle[12] = ipBox.getSelectedItem().toString();
		}
		if(portField.getText() == null || portField.getText().equals("")){
			notNull("仪表端口号1不能为空！");
			return null;
		}else{
			oracle[5] = portField.getText();
		}
		if(timeField.getText() == null || timeField.getText().equals("")){
			notNull("间隔时间不能为空！");
			return null;
		}else{
			BigDecimal a =new BigDecimal(timeField.getText());
			int b = a.multiply(new BigDecimal("60000")).intValue();
			oracle[6] = String.valueOf(b);
		}
		if(port2Field.getText() == null || port2Field.getText().equals("")){
			notNull("仪表端口号2不能为空！");
			return null;
		}else{
			oracle[7] = port2Field.getText();
		}
		if(message1Field.getText() == null || message1Field.getText().equals("")){
			notNull("发送电文1不能为空！");
			return null;
		}else{
			oracle[8] = message1Field.getText();
		}
		if(message2Field.getText() == null || message2Field.getText().equals("")){
			notNull("发送电文2不能为空！");
			return null;
		}else{
			oracle[9] = message2Field.getText();
		}
		if(tempDiffField.getText() == null || tempDiffField.getText().equals("")){
			notNull("零点监测报警差值不能为空！");
			return null;
		}else{
			oracle[10] = tempDiffField.getText();
		}
		if(scaleDiffField.getText() == null || scaleDiffField.getText().equals("")){
			notNull("标准卷报警差值不能为空！");
			return null;
		}else{
			oracle[11] = scaleDiffField.getText();
		}
		return oracle;
	}
	
	public String[] getWeight() {
		String[] oracle = new String[7];
		if(oracleIpField.getText() == null || oracleIpField.getText().equals("")){
			notNull("数据库IP不能为空！");
			return null;
		}else{
			oracle[0] = oracleIpField.getText();
		}
		if(oracleNameField.getText() == null || oracleNameField.getText().equals("")){
			notNull("数据库名称不能为空！");
			return null;
		}else{
			oracle[1] = oracleNameField.getText();
		}
		if(userField.getText() == null || userField.getText().equals("")){
			notNull("用户名不能为空！");
			return null;
		}else{
			oracle[2] = userField.getText();
		}
		if(passWordField.getText() == null || passWordField.getText().equals("")){
			notNull("密码不能为空！");
			return null;
		}else{
			oracle[3] = passWordField.getText();
		}
		if(ipBox.getSelectedItem() == null || ipBox.getSelectedItem().equals("")){
			notNull("仪表名称不能为空！");
			return null;
		}else{
			oracle[4] = ipBox.getSelectedItem().toString();
		}
		if(comboBox.getSelectedItem() == null || comboBox.getSelectedItem().equals("")){
			notNull("标准卷卷号不能为空！");
			return null;
		}else{
			oracle[5] = comboBox.getSelectedItem().toString();
		}
		if(tempWeight.getText() == null || tempWeight.getText().equals("")){
			notNull("实时重量不能为空！");
			return null;
		}else{
			oracle[6] = tempWeight.getText();
		}
		return oracle;
	}
	
	public void setTempWeight(String temp){
		String a = temp.replaceAll("^(0+)", "");
		String weight = null;
		if(a != null && !a.equals("")){
			weight = a;
		}else{
			weight = "0";
		}
		tempWeight.setText(weight);
		String[] weights = getWeight();
		if (weights == null) {
			return;
		}
		IDataSource dataSource = null;
		dataSource = new DefaultDataSource(weights[0],weights[1],weights[2],weights[3]);
		String sql2 = "UPDATE MES_MS_SCALE_CONFIG t SET T.RT_WEIGHT = '" + weight +
				"' WHERE T.METER_DESC = '"+ weights[4] +"'";
		if(flag && weight != null && !weight.equals("") && !weight.equals("0") && (Double.valueOf(weight) - Double.valueOf(tempDiffField.getText()) <= 0)){
			tempWeight.setForeground(Color.RED);
			String sql = "insert into MES_MS_STANDARD_RECORD (CREATED_BY, CREATED_DT,VERSION, METER_CODE, weights, WEIGHT_TIME)" +
					    "values ('ERROR',sysdate,1,'"+weights[4]+"',"+weight+",sysdate)";
			try {
				dataSource.insert(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(!flag && weight != null && !weight.equals("") && !weight.equals("0") && 
				( java.lang.Math.abs(Double.valueOf(weight) - Double.valueOf(scaleWeight.getText())) - Double.valueOf(scaleDiffField.getText()) >= 0)){
			tempWeight.setForeground(Color.RED);
			flag = true;
		}else{
			tempWeight.setForeground(Color.GREEN);
		}
		try {
			System.out.println(sql2);
			dataSource.update(sql2);
			dataSource.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void start(){
		start.setEnabled(false);
		end.setEnabled(true);
		weight.setEnabled(true);
		oracleIpField.setEnabled(false);
		oracleNameField.setEnabled(false);
		userField.setEnabled(false);
		passWordField.setEnabled(false);
		scaleIpField.setEnabled(false);
		ipBox.setEnabled(false);
		portField.setEnabled(false);
		port2Field.setEnabled(false);
		timeField.setEnabled(false);
		message1Field.setEnabled(false);
		message2Field.setEnabled(false);
		tempDiffField.setEnabled(false);
		scaleDiffField.setEnabled(false);
		tempWeight.setText("0");
		tempWeight.setForeground(Color.GREEN);
		enFlag = false;
	}
	
	public void end(){
		start.setEnabled(true);
		end.setEnabled(false);
		weight.setEnabled(false);
		save.setEnabled(false);
		ipBox.setEnabled(true);
		portField.setEnabled(true);
		port2Field.setEnabled(true);
		timeField.setEnabled(true);
		message1Field.setEnabled(true);
		message2Field.setEnabled(true);
		tempDiffField.setEnabled(true);
		scaleDiffField.setEnabled(true);
		enFlag = true;
	}
	
	public void errorend(){
		start.setEnabled(true);
		end.setEnabled(false);
		weight.setEnabled(false);
		save.setEnabled(false);
		ipBox.setEnabled(true);
		portField.setEnabled(true);
		port2Field.setEnabled(true);
		timeField.setEnabled(true);
		message1Field.setEnabled(true);
		message2Field.setEnabled(true);
		tempDiffField.setEnabled(true);
		scaleDiffField.setEnabled(true);
		enFlag = true;
	}
	
	public void weightStart(){
		weight.setEnabled(false);
	}
	
	public void weightEnd(){
		weight.setEnabled(true);
		save.setEnabled(true);
		flag = false;
	}
	
	public void save(){
		save.setEnabled(false);
	}
	
	/** 校验Ip地址是否合法
	 * @param addr
	 * @return
	 */
	public static boolean isIpValid(String addr){
		String[] ipStr = new String[4];
		int[] ipb = new int[4];
		StringTokenizer tokenizer = new StringTokenizer(addr, ".");
		int len = tokenizer.countTokens();

		if (len != 4)
		{
			return false;
		}
		try
		{
			int i = 0;
			while (tokenizer.hasMoreTokens())
			{
				ipStr[i] = tokenizer.nextToken();
				ipb[i] = (new Integer(ipStr[i])).intValue();

				if (ipb[i] < 0 || ipb[i] > 255)
				{
					return false;
				}
				i++;
			}
			if (ipb[0] > 0)
			{
				return true;
			}
		}
		catch (Exception e)
		{

		}
		return false;
	}
	
	public void notNull(String message) {
		JOptionPane.showMessageDialog(MenuFrame.this, message);
	}
}




