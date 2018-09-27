outputformat :="svg";
%compile the picutre to post script file (*.eps, viewed by gsviewer)
%,uncommnet "filenametemplate "%j-%c.svg""
prologues:=3;
filenametemplate "%j-%c.svg";
beginfig (1) ;
    numeric u;
    u:=1cm; %define the unit
    
    %draw a line with default pen width
    draw(0,0) -- (5cm,5cm);
    
    %move the line through x axis right by 5cm, y axis by 0cm
    %set pen with to 4pt, 8 times to default size
    pickup pencircle scaled 4pt;
    draw((0,0) -- (5cm,5cm)) shifted (5cm,0cm);    
    
    %set pen width to 8x default size
    pickup pencircle scaled 8;
    
    %draw a dot
    %orign represent (0,0)
    drawdot origin;
    drawdot (2.5cm,0);
    
    %draw a path, use cycle to draw a closed path
    draw(8cm,0) -- (8cm,1cm) -- (10cm,1cm) -- (10cm,0cm) -- cycle;    
    
    dotlabel.bot("A", (8cm,0));
    
    %draw curve:
    %defind dot coordinates
    z0 = origin; z1 = (5cm,5cm); z2 = (0,10cm); z3 = (-5cm, 5cm);
    draw z0 .. z1 .. z2 .. z3;
    
    %draw a label at the bottom of origin
    label.lrt("Origin", z0);
    
    pickup pencircle scaled 4pt;
    %use loop, step is 1
    for i = 0 upto 5:
        drawdot(3cm,i*cm);
    endfor
    
    %loop step is 2cm
    for i = 0 step 2 until 10:
        drawdot(4cm,i * cm);
    endfor
    
    %loop downto
    for i = 0 downto -5:
        drawdot(3cm,i*cm);
     endfor
     
    %equations
    %z5=(3cm,2cm);
    %z6=(-3cm,-2cm);
    %or:
    z5=-z6=(3cm,2cm);
    pickup pencircle scaled 1pt;
    draw z5--z6;
    %point z7 is at 1/3 of z5 to z6
    z7=1/3[z5,z6];
    %point z8 is at 1/3 of z6 to z5
    z8=1/3[z6,z5];
    
    pickup pencircle scaled 8;
    drawdot(z7);
    dotlabel.lrt("z7",z7);    
    drawdot(z8);
    dotlabel.lrt("z8", z8);
    
    %define a path and draw it:
    path square;
    pickup pencircle scaled 4;
    square := origin -- (0,2cm) -- (2cm, 2cm) -- (2cm, 0) -- cycle;
    draw square;
    
    %set path line style and color
    draw square shifted(2cm,2cm) withcolor blue dashed evenly;
    
    %fill a closed path
    fill square shifted(3cm,3cm) withcolor blue;
    
    %label position: top/bot/lft/rt/llft/lrt/ulft/urt
    %llft: lower left; urt: upper right
    a=5cm;
    b=7cm;
    z9=(0,0);
    z10=-z11=(b,0);
    z12=-z13=(0,a);
    draw z10 .. z12 .. z11.. z13.. cycle;
    draw z11--z9--z12;
    
    %change label font size to 4 times of default size
    defaultscale := 4;
    label.top("a", .5[z11,z9]);
    label.lft("b", .5[z9,z12]);
    
endfig ;
end.