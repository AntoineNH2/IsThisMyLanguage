%% test conditions
mot='éèàçöôazertyuiopmlkjhgfdsqnbvcxw';
i=1;
arret = 0;
double(mot)
for i = 1:length(mot)
    if (double(mot(i))>122 || double(mot(i))<65) || (double(mot(i))>90 && double(mot(i))<97) %ce n'est pas une lettre
        arret = 1;
        disp(mot(i))
    end
end
disp(['arret = ', num2str(arret)])

%%
a = 1;
if a<4 || a>3
    disp(a);
end