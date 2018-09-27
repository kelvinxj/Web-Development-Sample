outputformat :="svg";
%compile the picutre to post script file (*.eps, viewed by gsviewer)
%,uncommnet "prologues:=3;"
%prologues:=3;
filenametemplate "%j-%c.svg";
beginfig (1) ;
    %set the scale
    numeric u; u:=.5cm;
    
    %Draw a red dot at origin
    fill fullcircle scaled(1/5u) withcolor red ;
    
    % Add whitespace around the drawing
    setbounds currentpicture to
        boundingbox currentpicture enlarged 1/2u ;
    fill fullcircle scaled (1/5u)
        shifted (3u,5u) withcolor blue;
        
    %loop
    for i = 6u step 1/2u until 10u:
        draw fullcircle scaled(1/5u) shifted (8u, i)
            withcolor red;
    endfor;
    
    for i=0 step 1/2u until 3u:
        draw fullcircle scaled(1/5u) 
            shifted (2*i, (-i+9u)) withcolor green;
    endfor;
    
    %draw a line
    fill(5u,1u) -- (7u,5u) -- (7.5u, 5u) .. (8u, 3u)
        ..(9u, 3u) .. (7u,1/2u) .. cycle ;
endfig ;
end