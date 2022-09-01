import javax.swing.*;
import java.util.*;
import java.awt.event.*;

class Agriculture
{
    String name;
    public Agriculture()
    {
      name = null;
    }
    public static void main(String[] args)
    {
      new Frame();
    }
   
}

class Pesticides extends Agriculture
{
    boolean volatility;

    Pesticides(boolean b,String nm2)
    {   volatility=b;
        name= nm2;
    }
}

class Fertilizer extends Agriculture
{
    String type;

    Fertilizer(String r,String nm1)
    {   type=r;
        name=nm1;
    }
}

class FeedBack
{    double yi;
    String cropname;
    Pesticides p;
    Fertilizer fe;

    FeedBack(double y,String c,Pesticides pe,Fertilizer fer)
    {
        yi = y;
        cropname=c;
        p=pe;
        fe=fer;
    }
}


class Frame extends JFrame implements ActionListener
{

    Fertilizer s1, s2, s3, s4, s5;
    Pesticides d1, d2, d3, d4, d5;
    FeedBack f1, f2, f3, f4, f5;
    JLabel l,lFname, lPname;
    JButton f, p;
    JTextArea t1, t2;
    JScrollPane sb1, sb2;
    JTextField tFname, tPname;
    DefaultListModel<String> fertilizerList;
    DefaultListModel<String> pesticidesList;
    DefaultListModel<String> cropList;
    JList<String> fList;
    ArrayList<FeedBack> list;
    JList<String> pList;
    JList<String> cList;
    Frame()
    {    s1=new Fertilizer("Organic","FFF");
         s2=new Fertilizer("Inorganic","GGG");
         s3=new Fertilizer("Organic","HHH");
         s4=new Fertilizer("Inorganic","III");
         s5=new Fertilizer("Organic","JJJ");

         d1=new Pesticides(true,"AAA");
         d2=new Pesticides(false,"BBB");
         d3=new Pesticides(true,"CCC");
         d4=new Pesticides(false,"DDD");
         d5=new Pesticides(true,"EEE");

         f1=new FeedBack(99.2,"Basmati rice",d1,s1);
         f2=new FeedBack(88.7,"Wheat",d2,s2);
         f3=new FeedBack(76.6,"Warley",d3,s3);
         f4=new FeedBack(93.5,"Ragi",d4,s4);
         f5=new FeedBack(97.3,"Maize",d5,s5);

        list=new ArrayList<FeedBack>();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);


        f =new JButton("Feedback");
        p=new JButton("Purchase");
        l=new JLabel("");

        t1=new JTextArea();
        t2=new JTextArea();

        sb1 = new JScrollPane(t1);
        sb2 = new JScrollPane(t2);

        lFname=new JLabel("Quantity of Fertilizer: ");
        lPname=new JLabel("Quantity of Pesticide: ");

        tFname=new JTextField();
        tPname=new JTextField();


        fertilizerList = new DefaultListModel<>();
        fertilizerList.addElement("AAA 20");
        fertilizerList.addElement("BBB 25");
        fertilizerList.addElement("CCC 25");
        fertilizerList.addElement("DDD 30");
        fertilizerList.addElement("EEE 32");

        fList = new JList<>(fertilizerList);
        fList.setBounds(50,50, 75,75);

        pesticidesList = new DefaultListModel<>();
        pesticidesList.addElement("FFF 35");
        pesticidesList.addElement("GGG 45");
        pesticidesList.addElement("HHH 40");
        pesticidesList.addElement("III 50");
        pesticidesList.addElement("JJJ 30");

        pList = new JList<>(pesticidesList);
        pList.setBounds(130,50, 75,75);

        cropList = new DefaultListModel<>();
        cropList.addElement("Basmati rice");
        cropList.addElement("Wheat");
        cropList.addElement("Barley");
        cropList.addElement("Ragi");
        cropList.addElement("Maize");

        cList = new JList<>(cropList);
        cList.setBounds(210,50, 75,75);


        lFname.setBounds(50,150,150,30);
        lPname.setBounds(50,190,150,30);

        tFname.setBounds(200,150,120,30);
        tPname.setBounds(200,190,120,30);

        f.setBounds(50,230,150,30);
        p.setBounds(210,230,150,30);
        l.setBounds(50,140,120,30);
        sb1.setBounds(50,280,200,150);
        sb2.setBounds(270,280,200,150);

        add(fList); add(pList); add(cList); add(f); add(p); add(l); add(sb1); add(sb2);
        add(lFname); add(lPname); add(tFname); add(tPname);
        setSize(500,600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Online Agriculture");
        setVisible(true);
        f.addActionListener(this);
        p.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae)
        {
            String data = null;

                if (ae.getActionCommand()=="Feedback")
                {
                
                    if (cList.getSelectedIndex() != -1)
                    {
                        Iterator itr=list.iterator();
                        while(itr.hasNext())
                        {
                            FeedBack u1=(FeedBack)itr.next();

                            if(u1.cropname.equals(cList.getSelectedValue()))
                            {
                                data = "Crop name: " + cList.getSelectedValue() + " has a yield of "+u1.yi;

                                t1.append(data);
                                break;
                            }
                        }
                    }
                    else
                    {
                        t1.append("The feedback is not available for this particular crop");
                    }
                }

                if (ae.getActionCommand() == "Purchase")
                {
                    if(cList.isSelectionEmpty())
                    {
                        try
                        {
                            throw new Exception("Select a crop");
                        }catch (Exception e)
                        {
                            t2.append("Select a crop");
                        }


                    }

                    else if(tFname.getText().equals("") || tPname.getText().equals(""))
                    {
                        try
                        {
                           throw new Exception("Enter the quantity for the selected items");
                        }catch (Exception e)
                        {
                            t2.append("Enter the quantity for the selected items");
                        }

                    }
                    else if(fList.isSelectionEmpty() || pList.isSelectionEmpty())
                    {

                            try
                            {
                                throw new Exception("Select an item first");
                            }catch (Exception e)
                            {
                                t2.append("Select an item first");
                            }

                    }
                    else
                    {
                        t2.append("this item purchased\n");
                        t2.append(data);
                    }



                }
        }


    public static void main(String[] args)
    {
        Frame fr = new Frame();
    }
}