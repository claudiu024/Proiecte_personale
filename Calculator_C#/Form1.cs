using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Windows.Forms;

namespace ProiectPCLP
{
    public partial class Calculator : Form
    {
        public Calculator()
        {
            InitializeComponent();
            memory_hide.SendToBack();
            MR_button.Enabled = false;
            MC_button.Enabled = false;
            memory_show.Enabled = false;
            delete_button.Enabled = false;
           
        }

        bool Operator = false;
        bool eq = false;
        bool function_pressed = false;
        string o;
        double value = 0, input = 0,memory;
      

        private void Button_Click(object number, EventArgs e)
        {
            if (result.Text != "") 


                delete_button.Enabled = true;
            Button nr = (Button)number;

            if ((result.Text == "0") || (Operator))
            {
               
                result.Clear();
                Operator = false;
            }
            if(equation.Text=="0")
            { equation.ResetText(); }
            if (nr.Text == "," && result.Text=="")
            {
                    result.Text = "0"+ result.Text + nr.Text;
                
            }
            if (eq == false)
            {

                result.Text = result.Text + nr.Text;

                input = double.Parse(result.Text);

            }
            else {
                equation.ResetText();
                result.Text = nr.Text;
                
                input = double.Parse(result.Text);
            }
            eq = false;
        }

        private void Operator_click(object operation, EventArgs e)
        {
       
         
            if (!Operator)
            {
                if (value == 0 && !function_pressed)
                {
                    delete_button.Enabled = false;
                    value = input; 
                    equation.Text = (input).ToString() ;
                }
                else
                {
                  
                    equal_button.PerformClick();
                    value = double.Parse(result.Text);
                }

            }
            
            Button op = (Button)operation;
            o = op.Text;
            Operator = true;
            eq = false;
        }

        

        private void equal(object sender, EventArgs e)
        {
           
            delete_button.Enabled = false;
            if (!eq)
            {
                switch (o)
                {
                    case "+":
                        result.Text = (value + double.Parse(result.Text)).ToString();
                        break;

                    case "-":
                        result.Text = (value - double.Parse(result.Text)).ToString();
                        break;

                    case "*":
                        result.Text = (value * double.Parse(result.Text)).ToString();
                        break;

                    case "/":
                        result.Text = (value / double.Parse(result.Text)).ToString();
                        break;
                    default: break;
                }
                value = double.Parse(result.Text);

                if (!function_pressed)
                {
                    if (o == "*" || o == "/")
                    {
                        equation.Text = "(" + equation.Text + ")" + o + input;
                    }
                    else
                        equation.Text += o + input;
                    o = "";
                }
            }
            eq = true;
            delete_button.Enabled = true;
            function_pressed = false;

        }


        private void SwitchSign(object sender, EventArgs e)
        {
            if (!Operator)
            {
                if (eq)
                {
                    
                    value = double.Parse(result.Text);
                    value = -value;
                    equation.Text = "negate" + "(" + equation.Text + ")";
                    result.Text = (value).ToString();   
                }
                else
                {
                    input = -input;
                    
                    result.Text = (input).ToString();
                }
            }
        }

        private void SquareRoot(object sender, EventArgs e)
        {
            function_pressed = true;
            if(eq)
            {
                value = double.Parse(result.Text);
                value = Math.Sqrt(value);
                equation.Text +=   o +"sqrt" + "(" + result.Text + ")";
                result.Text = (value).ToString();
            }
           
         
            else {input = Math.Sqrt(input);
                equation.Text += o + "sqrt" + "(" + result.Text + ")";
                result.Text = (input).ToString(); }
           
        }

        private void Delete(object sender, EventArgs e)
        {
            
            equation.Text = "";
            result.Text = result.Text.Remove(result.Text.Length - 1);
            if (result.Text.Length == 0)
            {
                result.Text = "0";
                input= 0;
               
            }
           
        }
        private void square(object sender, EventArgs e)
        {

            function_pressed = true;
            if (eq)
            {
                value = double.Parse(result.Text);
                value = Math.Pow(value, 2);
                equation.Text += "square" + "(" + result.Text + ")";
                result.Text = (value).ToString();
            }


            else
            {
                input = Math.Pow(input,2);
                equation.Text += "square" + "(" + result.Text + ")";
                result.Text = (input).ToString();
            }

        }

        private void percentage(object sender, EventArgs e)
        {

            function_pressed = true;
            if (eq)
            {
                value = double.Parse(result.Text);
                value = value / 100;
                equation.Text += "1/100" + "(" + result.Text + ")";
                result.Text = (value).ToString();
            }


            else
            {
                input = input/100;
                equation.Text += "1/100" + "(" + result.Text + ")";
                result.Text = (input).ToString();
            }


        }

        private void inverse(object sender, EventArgs e)
        {
            function_pressed = true;
            if (eq)
            {
                value = double.Parse(result.Text);
                value = 1 / value;
                equation.Text += "1/" + result.Text;
                result.Text = (value).ToString();
            }


            else
            {
                input = 1 / input;
                equation.Text += "1/" + result.Text;
                result.Text = (input).ToString();
            }

        }

        private void MC(object sender, EventArgs e)
        {
            result.Text = "0";
            memory = 0;
            MR_button.Enabled = false;
            MC_button.Enabled = false;
            memory_show.Enabled = false;
            memory_list.ResetText();
        }
        

        private void MR_button_Click(object sender, EventArgs e)
        {

            result.Text = (memory).ToString();
            
        }

        private void M_add_button_Click(object sender, EventArgs e)
        {
            memory += double.Parse(result.Text);
            memory_list.Text+= " "+(memory).ToString();
        }

        private void memory_button_Click(object sender, EventArgs e)
        {
          
            memory_list.BringToFront();

 
          
        }

        private void M_substract_button_Click(object sender, EventArgs e)
        {
            memory -= double.Parse(result.Text);
            memory_list.Text += " " + (memory).ToString();
        }

       

        private void MS(object sender, EventArgs e)
        {
            memory = double.Parse(result.Text);
            MR_button.Enabled = true;
            MC_button.Enabled = true;
            memory_show.Enabled = true;
            memory_list.Text += " " + (memory).ToString();

        }

      
      

        private void memory_show_Click(object sender, EventArgs e)
        {
            memory_list.BringToFront();
            memory_show.SendToBack();
            M_add_button.SendToBack();
            M_substract_button.SendToBack();
            MS_button.SendToBack();
            MR_button.SendToBack();
            MC_button.SendToBack();

        }

        private void memory_hide_Click(object sender, EventArgs e)
        {
           
            memory_list.SendToBack();
            memory_hide.SendToBack();
            memory_show.BringToFront();
            
        }

       

        private void CE_button_Click(object sender, EventArgs e)
        {
            if ((eq) || (function_pressed)) equation.Text = "0";
            input = 0;
            result.Text = "0";
            
        }

        private void Clear(object sender, EventArgs e)
        {
            result.Clear();
            result.Text = equation.Text = "0";
            value = 0;
            input= 0;
        }


    }

}