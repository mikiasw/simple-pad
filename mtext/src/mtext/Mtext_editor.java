/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtext;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*; 
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.FileDialog;
import static java.awt.Font.PLAIN;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import static java.lang.Compiler.command;
import static java.nio.file.Files.size;
import java.util.Date;
import java.util.Scanner;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
//import static mtext_editor.Mtext_editor.active;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyledEditorKit.AlignmentAction;
/**
 *
 * @author mikias
 */
public class Mtext_editor extends JFrame implements ActionListener
{
    private static final String MAIN_TITLE = "MAY_EDITOR ";
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    private JFrame frame=new JFrame();
    private JPanel pan= new JPanel();
    FileDialog findReplaceDialog=null;
    private JTextArea text;
    private JTextPane editor__;
    private JLabel details;
    private JLabel sampleField;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu formatMenu;
    private JMenu font,font1,font2,font3,font4;
    private JMenuItem fstyle1,fstyle2,fstyle3,fstyle4,fstyle5;
    private JMenuItem colorstyle,colorstyle1,colorstyle2,colorstyle3,colorstyle4,colorstyle5,colorstyle6;
    private JMenuItem fname1,fname2,fname3,fname4,fname5,fname6,fname7,fname8,fname9,fname10;
    private JMenuItem fsize1,fsize2,fsize3,fsize4,fsize5,fsize6,fsize7,fsize8,fsize9,fsize10,fsize11;
    private static final String [] TEXT_ALIGNMENTS = {"Text Align", "Left", "Center", "Right", "Justified"};
    private JMenu viewMenu;
    private JMenu helpMenu;
    private JPopupMenu popup ; 
    private JComboBox<String> textAlignComboBox__;
    private JPanel bottom = new JPanel();
    private JCheckBox italicCheckBox;
    private JCheckBox boldCheckBox;
    private String file_name;
    private String currentFile = "Untitled Document";
    private ActionListener listener;
   //for printing purpose
    private PrinterJob job;
 public PageFormat format;
 private JMenuItem pageSetup;
 private JMenuItem printFile;
 private static final long serialVersionUID = 1L;
    public final static String AUTHOR_EMAIL = "mika@MAY.com";
    public final static String NAME = "MAY TEXT EDITOR";
        public final static String EDITOR_EMAIL = "MAY@MAY.COM";
    public final static double VERSION = 3.0;


    private boolean changed = false;
    static int active =0;
    String str=" ";

String str1=" ",str2=" ",str3=" ";
String str4=" ";

String str6=" ";
String str7=" ",str8=" ",str9=" ";
    int i=0;
    int len;
    int cl;
    int linecount;
    private String tle ;
    private Font f;
    private String command=" ";
    private String facename;
  private int fontstyle;
  private int fontsize;
  private File file__;
  private UndoManager undo;
  //private final String aboutText="This product is licensed and rights to MAY"
          //+ "and for more information visit www.MAY@may.com";
  private final String aboutText=      "<html><body><p>" +
        "Author: Mika<br />" +
        "Contact me at: " +"may@may.com"+
        "<a  "  + "?subject=About the MAY TEXT EDITOR APP'>" +   "</a>" +
                "<br /><br />" +
                "Modified By: Mika <br />" +
                "Contact me at: may@may.com" +  "?subject=About MAY TEXT EDITOR application" +   "</a>" +
        "</p></body></html>";

  private final String helpText="<html><body><p>" +
        "Name: " + Mtext_editor.NAME + "<br />" +
        "Version: " + Mtext_editor.VERSION +
          "<a"+"Use all the specified shortcuts and for more informtaion visit www.MAY@may.com"+"</a>"+
        "<br /><br />" +
          "Use all the specified shortcuts and for more informtaion visit www.MAY@may.com"+
          "</p></body></html>";;
  /*This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. */
enum UndoActionType {UNDO, REDO};
    
     public Mtext_editor(){  
          setTitle(NAME);
          setLocation(200,200);
           setSize(200, 200);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          pack();
          setVisible(true);
            editor__ = new JTextPane();
          
          //creating text fild area that have true line wraping 
          text=new JTextArea(40,40);
          //text.setLineWrap(true);
          //text.setWrapStyleWord(true);
          details = new JLabel();
          
         // listener = new ChoiceListener();
          
          
  // creating Jscrooll Pane inorder to add textfield area and add it to the frame
          JScrollPane scrollPane= new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

          scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
          scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
          //creating menu bar that consists of file, edit,format,view,help
              add(scrollPane,BorderLayout.CENTER);
          menuBar=new JMenuBar();
    popup = new JPopupMenu();
     
    setJMenuBar(menuBar);
   
    
    sampleField = new JLabel();
    
    text.add(editor__);
     editor__.requestFocusInWindow();
     editor__.setDocument(getNewDocument());
    bottom.add(details);
frame.add(bottom, BorderLayout.LINE_START);
    undoMan();

    //JMenu named File with menomonic F
    fileMenu=new JMenu("File");
   fileMenu.setMnemonic(KeyEvent.VK_F);
   menuBar.add(fileMenu);
   fileMenu.addSeparator();
    //Jmenu name Edit with menomonic E
    editMenu=new JMenu("Edit");
     editMenu.setMnemonic(KeyEvent.VK_E);
     menuBar.add(editMenu);
    //JMenu name Format with menomonic T
    formatMenu=new JMenu("Format");
    formatMenu.setMnemonic(KeyEvent.VK_T);
     //format Jmenuitem
    //font jmenuitem with mnemonic +
 font=new JMenu("Font");
font1=new JMenu("Font Style");
font2=new JMenu("Size");
font3=new JMenu("FontColor");
font4=new JMenu("Textalignment");


    formatMenu.add(font);
    formatMenu.add(font1);
formatMenu.add(font2);
formatMenu.add(font3);
formatMenu.add(font4);

font.add(fname1=new JMenuItem("Courier"));
font.add(fname2=new JMenuItem("Sans Serif"));
font.add(fname3=new JMenuItem("Monospaced"));
font.add(fname4=new JMenuItem("Symbol"));
font.add(fname5=new JMenuItem("Script MT Bold"));
font.add(fname6=new JMenuItem("Calibri (Body)"));
font.add(fname7=new JMenuItem("Times New Roman"));
font.add(fname8=new JMenuItem("Stencil"));
font.add(fname9=new JMenuItem("Old English Text MT"));
font.add(fname10=new JMenuItem("Bradley Hand ITC"));

font1.add(fstyle1=new JMenuItem("Regular"));
font1.add(fstyle2=new JMenuItem("Bold"));
font1.add(fstyle3=new JMenuItem("Italic"));
font1.add(fstyle4=new JMenuItem("Bold Italic"));
font1.add(fstyle5=new JMenuItem("Plain"));

font2.add(fsize1=new JMenuItem("12"));
font2.add(fsize2=new JMenuItem("14"));
font2.add(fsize3=new JMenuItem("18"));
font2.add(fsize4=new JMenuItem("20"));
font2.add(fsize5=new JMenuItem("22"));
font2.add(fsize6=new JMenuItem("24"));
font2.add(fsize7=new JMenuItem("26"));
font2.add(fsize8=new JMenuItem("28"));
font2.add(fsize9=new JMenuItem("36"));
font2.add(fsize10=new JMenuItem("48"));
font2.add(fsize11=new JMenuItem("72"));
        
font3.add(colorstyle=new JMenuItem("Font Color"));
font3.add(colorstyle1=new JMenuItem("Red"));
font3.add(colorstyle2=new JMenuItem("Blue"));
font3.add(colorstyle3=new JMenuItem("Green"));
font3.add(colorstyle4=new JMenuItem("Yellow"));
font3.add(colorstyle5=new JMenuItem("Gray"));
font3.add(colorstyle6=new JMenuItem("Pink"));


textAlignComboBox__ = new JComboBox<String>(TEXT_ALIGNMENTS);
textAlignComboBox__.setEditable(false);
//textAlignComboBox__.addItemListener(new TextAlignItemListener());
textAlignComboBox__.addActionListener(this);
font4.add(textAlignComboBox__);

fname1.addActionListener(this);
fname2.addActionListener(this);
fname3.addActionListener(this);
fname4.addActionListener(this);
fname5.addActionListener(this);
fname6.addActionListener(this);
fname7.addActionListener(this);
fname8.addActionListener(this);
fname9.addActionListener(this);
fname10.addActionListener(this);



fstyle1.addActionListener(this);
fstyle2.addActionListener(this);
fstyle3.addActionListener(this);
fstyle4.addActionListener(this);
fstyle5.addActionListener(this);

colorstyle.addActionListener(this);
colorstyle1.addActionListener(this);
colorstyle2.addActionListener(this);
colorstyle3.addActionListener(this);
colorstyle4.addActionListener(this);
colorstyle5.addActionListener(this);
colorstyle6.addActionListener(this);

fsize1.addActionListener(this);
fsize2.addActionListener(this);
fsize3.addActionListener(this);
fsize4.addActionListener(this);
fsize5.addActionListener(this);
fsize6.addActionListener(this);
fsize7.addActionListener(this);
fsize8.addActionListener(this);
fsize9.addActionListener(this);
fsize10.addActionListener(this);
fsize11.addActionListener(this);


menuBar.add(formatMenu);  
    
   

      menuBar.add(formatMenu);
    //JMenu named View with meomonic W
    viewMenu=new JMenu("View");
    viewMenu.setMnemonic(KeyEvent.VK_W);
     menuBar.add(viewMenu);
    //JMenu named Help with mneomonic H
    helpMenu=new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_H);
      menuBar.add(helpMenu);
    
    //JMenuItem of in each menu       print ????
    //File JMenuItem
    //New file JMenuItem with mneomonic N
    JMenuItem newMenuItem=new JMenuItem("New",KeyEvent.VK_N);
    KeyStroke newKeyStroke=KeyStroke.getKeyStroke("control N");
    newMenuItem.setAccelerator(newKeyStroke);
   newMenuItem.addActionListener(this);
    fileMenu.add(newMenuItem);
    
    //Open File JMenuItem with mnemonic O
    JMenuItem openMenuItem=new JMenuItem("Open",KeyEvent.VK_O);
    KeyStroke openKeyStroke=KeyStroke.getKeyStroke("control O");
    openMenuItem.setAccelerator(openKeyStroke);
    openMenuItem.addActionListener(new OpenListener());
        popup.add(openMenuItem);
    fileMenu.add(openMenuItem);
 
    
    //Save File JMenuItem with mnemonic S
    JMenuItem saveMenuItem=new JMenuItem("Save",KeyEvent.VK_S);
    KeyStroke saveKeyStroke=KeyStroke.getKeyStroke("control S");
    saveMenuItem.setAccelerator(saveKeyStroke);
    //saveMenuItem.addActionListener(saveListener);
    fileMenu.add(saveMenuItem);
   // saveMenuItem.setEnabled(true);
    //Save As File JMenuItem
    JMenuItem save_asMenuItem=new JMenuItem("SaveAs");
    save_asMenuItem.addActionListener(new SaveListener());
    fileMenu.add(save_asMenuItem);
    fileMenu.addSeparator();
    
    //Exit File JMenuItem with mnemonic Q
    JMenuItem exitMenuItem=new JMenuItem("Exit",KeyEvent.VK_Q);
    KeyStroke exitKeyStroke=KeyStroke.getKeyStroke("control Q");
    exitMenuItem.setAccelerator(exitKeyStroke);
   exitMenuItem.addActionListener(new ExitListener());
    
   //
        printFile = new JMenuItem("Print",KeyEvent.VK_P);
        KeyStroke printKeyStroke=KeyStroke.getKeyStroke("control P");
        printFile.setAccelerator(printKeyStroke);
        printFile.addActionListener(this);
        printFile.setPreferredSize(new Dimension(100, 20));
        printFile.setEnabled(true);

    
   //
   
    pageSetup = new JMenuItem("Page Setup");
        pageSetup.addActionListener(this);
        pageSetup.setPreferredSize(new Dimension(100, 20));
        pageSetup.setEnabled(true);

        fileMenu.add(pageSetup);
        fileMenu.add(printFile);

        fileMenu.add(exitMenuItem);
    //Edit JMenuItem 
    //undo eidt JMenuItem with mnemonic Z
    JMenuItem undoMenuItem=new JMenuItem("Undo",KeyEvent.VK_Z);
    KeyStroke undoKeyStroke=KeyStroke.getKeyStroke("control Z");
    undoMenuItem.setAccelerator(undoKeyStroke);
    undoMenuItem.addActionListener(this);
    editMenu.add(undoMenuItem);
    
    undoMenuItem.setText("Undo");
    JMenuItem redoMenuItem= new JMenuItem("Redo",KeyEvent.VK_Y);
    KeyStroke redoKeyStroke=KeyStroke.getKeyStroke("control Y");
    redoMenuItem.setAccelerator(redoKeyStroke);
    redoMenuItem.addActionListener(this);
    editMenu.add(redoMenuItem);        
    //cut edit jmenuitem with mnemonic X
   //Icon iconCut=new ImageIcon(getClass().getResource("/img/cut.gif"));
   //JMenuItem cutMenuItem=new JMenuItem("Cut",iconCut);
   JMenuItem  cutMenuItem=new JMenuItem("Cut",KeyEvent.VK_X);//iconCut);
    KeyStroke ctrlXKeyStroke = KeyStroke.getKeyStroke("control X");
    cutMenuItem.setAccelerator(ctrlXKeyStroke);
    cutMenuItem.addActionListener(new DefaultEditorKit.CutAction() );

    editMenu.add(cutMenuItem);
    //copy edit JMenuItem with mnemonic C
    JMenuItem copyMenuItem=new JMenuItem("Copy",KeyEvent.VK_C);
    KeyStroke ctrlCKeyStroke = KeyStroke.getKeyStroke("control C");
    copyMenuItem.setAccelerator(ctrlCKeyStroke);
    copyMenuItem.addActionListener(new DefaultEditorKit.CopyAction());
    editMenu.add(copyMenuItem);
    //paste eidt jmenuitem with mnemonic P
    JMenuItem pasteMenuItem= new JMenuItem("Paste",KeyEvent.VK_V);
    KeyStroke pasteKeyStroke=KeyStroke.getKeyStroke("control V");
    pasteMenuItem.setAccelerator(pasteKeyStroke);
    pasteMenuItem.addActionListener(new DefaultEditorKit.PasteAction());
    editMenu.add(pasteMenuItem);
    // delate edit jmenuitemwith mnimonic D
    JMenuItem delateMenuItem= new JMenuItem("Delete",KeyEvent.VK_D);
    KeyStroke delateKeyStroke=KeyStroke.getKeyStroke("control D");
    delateMenuItem.setAccelerator(delateKeyStroke);
       delateMenuItem.addActionListener(this);
       editMenu.add(delateMenuItem);
       // selectall 
         JMenuItem selectAllMenuItem= new JMenuItem("Select_All",KeyEvent.VK_A);
    KeyStroke selectAllKeyStroke=KeyStroke.getKeyStroke("control A");
selectAllMenuItem.setAccelerator(selectAllKeyStroke);
      selectAllMenuItem.addActionListener(this);
       editMenu.add(selectAllMenuItem);
    //find edit jmenuitem with mnimonic f
    JMenuItem findMenuItem=new JMenuItem("Find",KeyEvent.VK_D);
    KeyStroke findKeyStroke=KeyStroke.getKeyStroke("control F ");
    findMenuItem.setAccelerator(findKeyStroke);
    findMenuItem.addActionListener(this);
    findMenuItem.setEnabled(true);
   
    
    //findMenuItem.addActionListener();
    editMenu.add(findMenuItem);
    JMenuItem replaceMenuItem = new JMenuItem("Replace",KeyEvent.VK_R);
    KeyStroke replacKeyStroke=KeyStroke.getKeyStroke("control R");
    replaceMenuItem.setAccelerator(replacKeyStroke);
    replaceMenuItem.addActionListener(this);
    
    editMenu.add(replaceMenuItem);
    JMenuItem timedate=new JMenuItem("Time/Date",KeyEvent.VK_T);
    KeyStroke timedateKeyStroke=KeyStroke.getKeyStroke("control T");
    timedate.setAccelerator(timedateKeyStroke);
    timedate.addActionListener(this);
    editMenu.add(timedate);

      //view Jmenuitem
   //statusbar jmenu item
   JMenuItem statusBarMenuItem=new JMenuItem("StatusBar");
   statusBarMenuItem.addActionListener(this);
   viewMenu.add(statusBarMenuItem);
 
   //help Jmenuitem
   //About jmenuitem
   JMenuItem aboutMenuItem=new JMenuItem("About");
    helpMenu.add(aboutMenuItem); 
       aboutMenuItem.addActionListener(this);
       JMenuItem helpMenuItem=new JMenuItem("Help");
       helpMenu.add(helpMenuItem);
       helpMenuItem.addActionListener(this);
       facename = "Serif";
   fontsize = 24;
    fontstyle = Font.PLAIN;
  //     createControlPanel();
       JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
		JPanel toolBarPanel = new JPanel();
		toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.PAGE_AXIS));
		toolBarPanel.add(panel1);
		
 JScrollPane editorScrollPane = new JScrollPane(editor__);
		frame.add(toolBarPanel, BorderLayout.NORTH);
                
      editorScrollPane.setPreferredSize(new Dimension(250,250));
      frame.add(editorScrollPane, BorderLayout.CENTER);
//setSampleFont_Menu();,
       setSize(FRAME_WIDTH, FRAME_HEIGHT);
       

//pan.add(text);

f=new Font("Monotype Corsiva",Font.PLAIN,15);
text.setFont(f);

///to be removed

//
     }
     private void setFrameTitleWithExtn(String titleExtn) {

		frame.setTitle(MAIN_TITLE + titleExtn);
	}
	
     private UndoManager undoMan() {
        // Listener for undo and redo functions to document
        undo = new UndoManager();
        text.getDocument().addUndoableEditListener(new UndoableEditListener() {

            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undo.addEdit(e.getEdit());
            }
        });return undo;
    }
        
	 private StyledDocument getNewDocument() {
    
        StyledDocument doc = new DefaultStyledDocument();
        doc.addUndoableEditListener(new UndoEditListener());
        return doc;
    } 
         
public void actionPerformed(ActionEvent ae)
{


command=(String)ae.getActionCommand();
if(command.equals("New"))
{
    int confirm=JOptionPane.showConfirmDialog(null, "Would you like to save the file");
               if(confirm==JOptionPane.YES_OPTION)
               {
                     int chooserStatus  ;
           // If the user selected Save As, or the contents
         
         // of the editor have not been saved, use a file
         // chooser to get the file name. Otherwise, save        
         // the file under the current file name.309310     
        
       {             JFileChooser chooser = new JFileChooser();
         chooserStatus = chooser.showSaveDialog(null);
                     if (chooserStatus == JFileChooser.APPROVE_OPTION)
         {
             // Get a reference to the selected file.              
             File selectedFile = chooser.getSelectedFile();
             // Get the path of the selected file
             file_name = selectedFile.getPath(); 
             
            if (!saveFile1(file_name))
         {
             JOptionPane.showMessageDialog(null,"Error saving " + file_name,"Error",JOptionPane.ERROR_MESSAGE);          
         } 
             
             dispose();
Mtext_editor note1= new Mtext_editor();

note1.setSize(500,500);
note1.setVisible(true);
            
            
         //  newMenuItem.addActionListener(new filesaver());
             
         }
  
         // Save the file.
         
     }
               }
               
               else if(confirm==JOptionPane.NO_OPTION )
               {
                dispose();
Mtext_editor note1= new Mtext_editor();

note1.setSize(500,500);
note1.setVisible(true);
               }

}


if(command.equals("Delete"))
{ try{
String msg=text.getSelectedText();
i=text.getText().indexOf(msg);
text.replaceRange(" ",i,i+msg.length());
}
catch
        (Exception e){}
}


if(command.equals("Undo")){
            try {
                undo.undo();
            } catch(CannotUndoException cu) {
                cu.printStackTrace();
            }
        } 
 
if(command.equals("Redo")){
            try {
                undo.redo();
            } catch(CannotUndoException cur) {
                cur.printStackTrace();
            }
            catch(Exception e){}
        } 
if(command.equals("Time/Date")){
Date date=new Date();
text.setText(date.toString());
}

 if(command.equals("Courier"))
{

String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font("Courier",fontStyle,fontSize);
text.setFont(f);
}
if(command.equals("Sans Serif"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font("Sans Serif",fontStyle,fontSize);
text.setFont(f);
}
if(command.equals("Monospaced"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font("Monospaced",fontStyle,fontSize);
text.setFont(f);
}

if(command.equals("Symbol"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font("Symbol",fontStyle,fontSize);
text.setFont(f);
System.out.println(f.getFamily());
}
if(command.equals("Script MT Bold")){
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();
f=new Font("Script MT Bold",fontStyle,fontSize);
text.setFont(f);

}
if(command.equals("Times New Roman")){
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();
f=new Font("Times New Roman",fontStyle,fontSize);
text.setFont(f);

}
if(command.equals("Stencil")){
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();
f=new Font("Stencil",fontStyle,fontSize);
text.setFont(f);

}
if(command.equals("Old English Text MT")){
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();
f=new Font("Old English Text MT",fontStyle,fontSize);
text.setFont(f);

}
if(command.equals("Bradley Hand ITC")){
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();
f=new Font("Bradley Hand ITC",fontStyle,fontSize);
text.setFont(f);

}





if(command.equals("Regular"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,Font.PLAIN,fontSize);
text.setFont(f);
}
if(command.equals("Bold"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,Font.BOLD,fontSize);
text.setFont(f);
}
if(command.equals("Italic"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,Font.ITALIC,fontSize);
text.setFont(f);
}
if(command.equals("Bold Italic"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,Font.BOLD|Font.ITALIC,fontSize);
text.setFont(f);
}
if(command.equals("Plain")){
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();
f=new Font(fontName,Font.PLAIN,fontSize);
text.setFont(f);
}
if(command.equals("12"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,12);
text.setFont(f);
}

if(command.equals("14"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,14);
text.setFont(f);
}
if(command.equals("18"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,18);
text.setFont(f);
}
if(command.equals("20"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,20);
text.setFont(f);
}
if (command.equals("22")){
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();
f=new Font(fontName,fontStyle,22);
text.setFont(f);
}
if(command.equals("24"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,24);
text.setFont(f);
}
if(command.equals("26"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,26);
text.setFont(f);
}
if(command.equals("28"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,28);
text.setFont(f);
}
if(command.equals("36"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,36);
text.setFont(f);
}
if(command.equals("48"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,48);
text.setFont(f);
}
if(command.equals("72"))
{
String fontName=f.getName();
String fontFamily=f.getFamily();
int fontSize=f.getSize();
int fontStyle=f.getStyle();

f=new Font(fontName,fontStyle,72);
text.setFont(f);
}
if(command.equals("Font Color")){
Color newColor =JColorChooser.showDialog(frame, "Choose a color", Color.BLUE);
text.setForeground(newColor);
 

}
if(command.equals("Red")){
text.setForeground(Color.RED);
}
if(command.equals("Green")){
text.setForeground(Color.GREEN);}
if(command.equals("Yellow")){
text.setForeground(Color.YELLOW);}
if(command.equals("Blue")){
text.setForeground(Color.BLUE);}
if(command.equals("Gray")){
text.setForeground(Color.GRAY);}
if(command.equals("Pink")){
text.setForeground(Color.PINK);}


if(command.equals("Page Setup")){
    job=PrinterJob.getPrinterJob();
    format=job.pageDialog(job.defaultPage());
    
}
 if(command.equals("Print")){
            job = PrinterJob.getPrinterJob();
            if(job.printDialog()) {
                try {
                    job.print();
                } catch (PrinterException err) {
                    err.printStackTrace();
                }
            }
        }


    if(command.equals("StatusBar"))
	{
		try{
			if(active ==0)
			{
				File f = new File(tle+".txt");
				details.setText("Size: "+f.length());
			
                        cl= text.getText().length();
	linecount = text.getLineCount();
	details.setText("Length: "+cl+" Line: "+linecount);
                        }
		}
		catch (Exception e)
		{
			
		}
                
	}
    if (command.equals("About")){
    JOptionPane.showMessageDialog(frame,aboutText,"MAY TEXT EDITOR",
    JOptionPane.INFORMATION_MESSAGE);}
    if(command.equals("Help")){
    JOptionPane.showMessageDialog(frame,helpText,"MAY TEXT EDITOR",JOptionPane.QUESTION_MESSAGE);}
if(command.equals("Select_All"))
{
String strText=text.getText();
int strLen=strText.length();
text.select(0,strLen);
}
if(command.equals("Find")||command.equals("Replace")){
     new Find(text);
/*if(text.getText().length()==0)
    return;// text box have no text 
if(findReplaceDialog==null){
    //Color newColor =JColorChooser.showDialog(frame, "Choose a color", Color.BLUE);
    //FileDialog findReplaceDialog= File.showDialog(frame,true);
    //findReplaceDialog=new FileDialog(text);
    //findReplaceDialog.showDialog(Mtext_editor.this.f,true);
    //findReplaceDialog.showDialog (frame,true);//find
    */
}////tobe removed
if(command.equals("TEXT_ALIGNMENTS")){

            
            
            String alignmentStr = (String) textAlignComboBox__.getSelectedItem();         
            int newAlignment = textAlignComboBox__.getSelectedIndex() - 1;
            // New alignment is set based on these values defined in StyleConstants:
            // ALIGN_LEFT 0, ALIGN_CENTER 1, ALIGN_RIGHT 2, ALIGN_JUSTIFIED 3
            textAlignComboBox__.setAction(new AlignmentAction(alignmentStr, newAlignment)); 
            textAlignComboBox__.setSelectedIndex(0); // initialize to (default) select
            editor__.requestFocusInWindow();
        }}////
/*
if(command.equals("Find"))
{  
if(Mtext_editor.this.text.getText().length()==0)  
    return; // text box have no text  
  
if(findReplaceDialog==null)  
    statusBar.setText("Use Find option of Edit Menu first !!!!");  
else  
    findReplaceDialog.findNextWithSelection();  
*/



private class ExitListener implements ActionListener
     {public void actionPerformed(ActionEvent e)
     {               command=(String)e.getActionCommand();
     if(command.equals("Exit")){
         int confirm=JOptionPane.showConfirmDialog(null, "Would you like to save the file");
               if(confirm==JOptionPane.YES_OPTION)
               {
                     int chooserStatus  ;
           // If the user selected Save As, or the contents
         
         // of the editor have not been saved, use a file
         // chooser to get the file name. Otherwise, save        
         // the file under the current file name.309310     
        
       {             JFileChooser chooser = new JFileChooser();
         chooserStatus = chooser.showSaveDialog(null);
                     if (chooserStatus == JFileChooser.APPROVE_OPTION)
         {
             // Get a reference to the selected file.              
             File selectedFile = chooser.getSelectedFile();
             // Get the path of the selected file
             file_name = selectedFile.getPath(); 
             
            if (!saveFile1(file_name))
         {
             JOptionPane.showMessageDialog(null,"Error saving " + file_name,"Error",JOptionPane.ERROR_MESSAGE);          
         } 
              System.exit(0);
             
            
         //  newMenuItem.addActionListener(new filesaver());
             
         }
  
         // Save the file.
         
     }
               }
               
               else if(confirm==JOptionPane.NO_OPTION )
               {    System.exit(0);
                   
               }}}}
 void saveOld() {

    if(changed) {

            if(JOptionPane.showConfirmDialog(this, "Would you like to save and close "+ currentFile +" ?","Save",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION)

                   saveFile(currentFile);

    }

}
 
private void saveFile(String fileName) {

    try {

            FileWriter fw = new FileWriter(fileName);

            text.write(fw);

            fw.close();

            currentFile = fileName;
            //important 
            setTitle("Mtext_file " + currentFile);

            changed = false;
            

           //SaveListener.setEnabled(false);

    }

    catch(IOException e) {

    }

}

private boolean saveFile1(String fileName) {

    try {

            FileWriter fw = new FileWriter(fileName);

            text.write(fw);

            fw.close();

            currentFile = fileName;
            //important 
            setTitle("Mtext_file " + currentFile);

            changed = false;
            return true;

           //SaveListener.setEnabled(false);

    }

    catch(IOException e) {

    }
return false;
}

private class HelpListener implements ActionListener
     {public void actionPerformed(ActionEvent e)
     {
         System.out.println("this is simple desktop applicationn ");
     }    }
    
     
     
     


     private class OpenListener implements ActionListener   {     
     public void actionPerformed(ActionEvent e)      {
         int chooserStatus;
          JFileChooser chooser = new JFileChooser();
     chooserStatus = chooser.showOpenDialog(null);
     if (chooserStatus == JFileChooser.APPROVE_OPTION)    {
    // Get a reference to the selected file
    File selectedFile = chooser.getSelectedFile(); 
    // Get the path of the selected file.
    file_name = selectedFile.getPath();           
            // Open the file.
            if (!openFile(file_name))
            {
                JOptionPane.showMessageDialog(null,"Error reading " +file_name, "Error",JOptionPane.ERROR_MESSAGE);   
            }
}       
     }
     }         
    
     private boolean openFile(String file_name)
{ 
boolean success;

String inputLine, editorString = "";

try
{             // Open the file

File file = new File(file_name);
// Read the file contents into the editor.
    try (Scanner inputFile = new Scanner(file)) {
        // Read the file contents into the editor.
        while (inputFile.hasNext())             {
// Read a line from the file.
inputLine = inputFile.nextLine();
// Append it to the string to display
// in the editor.
editorString = editorString +  inputLine + "\n";
        }// Display the string that was read from the
// file in the editor.
text.setText(editorString);
// Close the file.
    }
// Indicate that everything went OK.
success = true;          
}          
catch (IOException e)         {
// Something went wrong.
success = false;      
}
// Return our status.          
return success;       

}
     private class SaveListener implements ActionListener{    
         public void actionPerformed(ActionEvent e)
  {        
          int chooserStatus  ;
           // If the user selected Save As, or the contents
         
         // of the editor have not been saved, use a file
         // chooser to get the file name. Otherwise, save        
         // the file under the current file name.309310     
         if ("Save As".equals(e.getActionCommand()) || file_name == null)
         {             JFileChooser chooser = new JFileChooser();
         chooserStatus = chooser.showSaveDialog(null);
                     if (chooserStatus == JFileChooser.APPROVE_OPTION)
         {
             // Get a reference to the selected file.              
             File selectedFile = chooser.getSelectedFile();
             // Get the path of the selected file
             file_name = selectedFile.getPath();      
         }
  
         // Save the file.
         if (!saveFile(file_name))
         {
             JOptionPane.showMessageDialog(null,"Error saving " + file_name,"Error",JOptionPane.ERROR_MESSAGE);          }
     }
     /**
      * The saveFile method saves the contents of the
      * text area to a file. The method returns true if
      * the file was saved successfully, or false if an
      * error occurred.    @param filename The name of the file
      * @return true if successful, false otherwise.344       */
  }
        private  boolean saveFile(String file_name ) {
                   boolean success;         
                   String editorString ;
                   PrintWriter outputFile;
   try 
           {           
         // Open the file.          
         outputFile = new PrintWriter(file_name);
         // Write the contents of the text area
         // to the file.
         editorString = text.getText();
         outputFile.print(editorString);
         // Close the file.
         outputFile.close();
         // Indicate that everything went OK.
         success = true;}
     catch  (Exception e) 
             {
         // Something went wrong.
         success = false;
     }
   // Return our status.
   return success;
     }

     


            /**          The openFile method opens the file specified by
             * filename and reads its contents into the text
             * area. The method returns true if the file was
             * opened and read successfully, or false if an error occurre.@param filename The name of the file to open.
             */


/** 
 * Private inner class that handles the event that
 * is generated when the user selects Save or Save
 * As from the file menu.    */
    /**
     *
     */
    

    
     

  
  

     } 
     
     
     ///to be 
      

private class UndoEditListener implements UndoableEditListener {

        @Override
        public void undoableEditHappened(UndoableEditEvent e) {

            undo.addEdit(e.getEdit()); // remember the edit
        }
    }
// to be removed
 private class TextAlignItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if ((e.getStateChange() != ItemEvent.SELECTED) ||
                (textAlignComboBox__.getSelectedIndex() == 0)) {
            
                return;
            }
            
            String alignmentStr = (String) e.getItem();         
            int newAlignment = textAlignComboBox__.getSelectedIndex() - 1;
            // New alignment is set based on these values defined in StyleConstants:
            // ALIGN_LEFT 0, ALIGN_CENTER 1, ALIGN_RIGHT 2, ALIGN_JUSTIFIED 3
            textAlignComboBox__.setAction(new AlignmentAction(alignmentStr, newAlignment)); 
            textAlignComboBox__.setSelectedIndex(0); // initialize to (default) select
            editor__.requestFocusInWindow();
        }
    } // TextAlignItemListener

//



     public static void main(String[] args) {
        // TODO code application logic here
         Mtext_editor may=new Mtext_editor();
       
    
 }
 }
        
    
    
