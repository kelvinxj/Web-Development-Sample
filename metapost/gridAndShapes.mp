outputformat :="svg";
%compile the picutre to post script file (*.eps, viewed by gsviewer)
%,uncommnet "prologues:=3;"
%prologues:=3;
%build with: mpost --mem=metafun full.mp
filenametemplate "%j-%c.svg";
beginfig (1) ; 
    %draw background grid
    numeric u ; u := .5cm;
    
    picture grid ;
    grid := image (
    width := 10u ;
    height := 10u ;
    draw vlingrid (0, 10, 1, width, height)
    withcolor .1white ;
    draw hlingrid (0, 10, 1, height, width)
    withcolor .1white ;
    ) ;
    draw grid withpen pencircle scaled 1/20 ;
    
    %draw original label:
    label ("0", (-1/4u,-1/4u));
    
    %draw vertical labels
    label ("1", (-1/4u,1u)) ;
    label ("2", (-1/4u,2u)) ;
    label ("6", (-1/4u,6u)) ;
    label ("9", (-1/4u,9u)) ;
    
    %draw horizontal labels
    label ("1", (1u,-1/4u)) ;
    label ("9", (9u,-1/4u)) ;
    
    %draw horizontal labels
    label("0", (-1/4u, -1/4u));
    
    %draw a filled circle at original point
    fill fullcircle scaled (1/5u) withcolor red ;
    fill fullcircle scaled (1/5u) shifted (3u,5u)
        withcolor blue ;
    fill fullcircle scaled (1/5u)
        shifted ((4u+1/8u),3.25u) withcolor green ;
        
    for i = 6u step 1/2u until 10u :
        draw fullcircle scaled (1/5u) shifted (8u,i)
            withcolor red ;
    endfor ;
    
    % Define the black object
    picture curvy ;
    curvy := image (
        fill (5u,1u) -- (7u,5u) -- (7.5u,5u)
            .. (8u,3u) .. (9u,3u) .. (7u,1/2u)
            .. cycle ; );
    % Draw the black object
    draw curvy ;

    
    % Draw bounding box around the object
    draw llcorner curvy -- lrcorner curvy
    -- urcorner curvy -- ulcorner curvy
    -- cycle withcolor blue;
    
    % Add whitespace
    setbounds currentpicture
        to boundingbox currentpicture enlarged 1/2u ;
        
    % Scale up to be readable
    currentpicture := currentpicture xsized 20cm ;
    % -- end full example --

    
endfig ;
end