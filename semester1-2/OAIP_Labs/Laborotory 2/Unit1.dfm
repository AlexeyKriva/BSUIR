object Form1: TForm1
  Left = 252
  Top = 169
  Width = 870
  Height = 450
  Caption = 'Form1'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 256
    Top = 56
    Width = 12
    Height = 13
    Caption = 'a='
  end
  object Label2: TLabel
    Left = 256
    Top = 88
    Width = 12
    Height = 13
    Caption = 'c='
  end
  object Label3: TLabel
    Left = 256
    Top = 120
    Width = 11
    Height = 13
    Caption = 'z='
  end
  object Edit1: TEdit
    Left = 272
    Top = 56
    Width = 81
    Height = 21
    TabOrder = 0
  end
  object Edit2: TEdit
    Left = 272
    Top = 88
    Width = 81
    Height = 21
    TabOrder = 1
  end
  object Edit3: TEdit
    Left = 272
    Top = 120
    Width = 81
    Height = 21
    TabOrder = 2
  end
  object Memo1: TMemo
    Left = 256
    Top = 160
    Width = 225
    Height = 89
    Lines.Strings = (
      'Memo1')
    ScrollBars = ssVertical
    TabOrder = 3
  end
  object Button1: TButton
    Left = 320
    Top = 264
    Width = 89
    Height = 25
    Caption = #1055#1091#1089#1082
    TabOrder = 4
    OnClick = Button1Click
  end
  object RadioGroup1: TRadioGroup
    Left = 384
    Top = 56
    Width = 97
    Height = 81
    Caption = #1042#1099#1073#1086#1088' '#1092#1091#1085#1082#1094#1080#1080
    Items.Strings = (
      '2*x'
      'x^2'
      'x/3')
    TabOrder = 5
  end
  object Button2: TButton
    Left = 320
    Top = 304
    Width = 91
    Height = 25
    Caption = #1054#1095#1080#1089#1090#1082#1072' Memo'
    TabOrder = 6
    OnClick = Button2Click
  end
end
