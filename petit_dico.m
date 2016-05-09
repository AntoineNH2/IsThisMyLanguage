%% verif mot

mot1 = double('salut');
mot2 = double('salutation');
mot3 = double('indentation');


mat3D = zeros(256,256,256);
%%
for ind = 1:3
    if ind == 1
        mot = mot1;
    elseif ind ==2
        mot = mot2;
    else
        mot = mot3;
    end
    disp(mot)
    iPrec2 = 1;
    iPrec = 1;
    
    for i=1:length(mot)
        iSuiv = double(mot(i));
        mat3D(iPrec2,iPrec,iSuiv) =mat3D(iPrec2,iPrec,iSuiv) +1;
        iPrec2 = iPrec;
        iPrec = iSuiv;        
    end
end