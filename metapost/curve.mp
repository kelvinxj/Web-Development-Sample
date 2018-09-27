outputformat :="svg";
%compile the picutre to post script file (*.eps, viewed by gsviewer)
%,uncommnet "prologues:=3;"
prologues:=3;
filenametemplate "%j-%c.svg";
beginfig (1) ;
    %set line with
    %pickup pencircle scaled 4pt;
    
    %numeric u;
    %u:=.5cm;
    
    
    
    z0 = (0,0); z1 = (60,40);
    z2 = (40,90); z3 = (10,70);
    z4 = (30,50);
    draw z0..z1..z2..z3..z4;
    
   %label("z0",z0);
    dotlabel.bot(btex $\sqrt x$ etex,z0);
    dotlabel.rt("z1",z1);
    dotlabel.bot("z2",z2);
    dotlabel.rt("z3",z3);
    dotlabel.bot("z4",z4);
    
    
    %currentpicture := currentpicture xsized 20cm ;
endfig ;
end