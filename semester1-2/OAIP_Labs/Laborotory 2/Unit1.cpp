//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Unit1.h"
#include <math.h>
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"
TForm1 *Form1;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent* Owner)
        : TForm(Owner)
{
}
//---------------------------------------------------------------------------

void __fastcall TForm1::FormCreate(TObject *Sender)
{
Edit1->Clear();
Edit2->Clear();
Edit3->Clear();
Memo1->Clear();
Memo1->Lines->Add("Laborotory work 2");
RadioGroup1->ItemIndex = 0;
}
//---------------------------------------------------------------------------

void __fastcall TForm1::Button1Click(TObject *Sender)
{
double x,y,z,a,b,c,d,f;
a = StrToFloat(Edit1->Text);
b = StrToFloat(Edit2->Text);
z = StrToFloat(Edit3->Text);

if (z<=1)
{
x = pow(z,2)+1;
Memo1->Lines->Add("x = (z^2)+1 = " +FloatToStrF(x,ffFixed,6,2));
}
else
{
x = 1/sqrt(z-1);
Memo1->Lines->Add("x = 1/(z-1)^(1/2) = " +FloatToStrF(x,ffFixed,6,2));
}

switch(RadioGroup1->ItemIndex)
{
case 0: f = 2*x;
Memo1->Lines->Add("������� ������� f(x)=2x, f(x)= "+FloatToStrF(f,ffFixed,6,2));
break;
case 1: f = pow(x,2);
Memo1->Lines->Add("������� ������� f(x)=x^2, f(x)= "+FloatToStrF(f,ffFixed,6,2));
break;
case 2: f = x/3.;
Memo1->Lines->Add("������� ������� f(x)=x/3, f(x)= "+FloatToStrF(f,ffFixed,6,2));
break;
}

b = a*pow(sin(pow(f,2)-1),3);
d = c*log(fabs(x))+exp(x);
y = b+d;
Memo1->Lines->Add("Y ����� " +FloatToStrF(y,ffFixed,6,2));
}
//---------------------------------------------------------------------------
void __fastcall TForm1::Button2Click(TObject *Sender)
{
Memo1->Clear();
}
//---------------------------------------------------------------------------

