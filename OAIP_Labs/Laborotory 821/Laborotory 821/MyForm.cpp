#include "MyForm.h"

using namespace System;
using namespace System::Windows::Forms;

[STAThreadAttribute]
namespace Laborotory 821
{
	public partial class Form1 :Form
	{
		public Form1();
		{
			InitializeComponent();
		}

		private void button1_Click(object sender, EventArgs e)
		{
			double a = -10, b = 10, h = 0.1, x, y;
			this.chart1.Series[0].Points.Clear();
			x = a;
			while (x <= b)
			{
				y = Math.Sin(x);
				this.chart1.Series[0].Points.AddXY(x, y);
				x += h;
			}
		}
	};
}



