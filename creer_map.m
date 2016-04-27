%% Crée le tableau
%function map=creer_map(langue)  %à mettre en string 's'

global langue stat
langue = 'fr';
filepath = [num2str(langue), '.txt'];

fileID = fopen(filepath);
C = textscan(fileID,'%s %d');
fclose(fileID);
%celldisp(C)

% % lettre à la suite
% % lettre +2 à la suite
% % début de mot
% % fin de mot
% % taille des mots

%% String => ascii et ascii => string
% s=double('hello')
% 
% s =
% 
%    104 101 108 108 111
% 
% %Convert back as follows
% 
% >> [char(s)]
% 
% ans =
% hello

%% Variable pour les statistiques
%taille mot
stat.taille = zeros(100,1);

stat.lettres_1 = zeros(255);    %lettre +1  
stat.lettres_2 = zeros(255);    %lettre +2
stat.start_1 = zeros(255,1);    %lettre qui commence +1


%%

tic
dim_dic =size(C{1}); %les mots
k=0;
disp('Calculs en cours...')
while k<dim_dic(1)
    k=k+1;
    if mod(k,2000)==0
%         disp(['Etape n°',num2str(k)]);
    end
    mot = C{1}{k};
    dim_mot= size(mot);
    i=1;
    while i<=dim_mot(2) 
        if double(mot(i))>255
            k=k+1;
            %disp(['Etape n°',num2str(k)]);
            mot = C{1}{k};
            dim_mot= size(mot);
            i=1;
        end
        %enregistre les stat
        if i == 1%start+1
            stat.start_1(double(mot(i)))= stat.start_1(double(mot(i)))+1;
%             disp('start i=1 fait')
        else
            stat.lettres_1(double(mot(i-1)),double(mot(i)))=stat.lettres_1(double(mot(i-1)),double(mot(i)))+1;

            if i>2
                stat.lettres_2(double(mot(i-2)),double(mot(i)))=stat.lettres_2(double(mot(i-2)),double(mot(i)))+1;
            end        
            if mot(i)=='/';
%                 disp('mot(i) = /')
                mot(i:dim_mot(2)) = [];
                break
            end
        end
        i=i+1;
%         disp('fin de la lettre')
    end
    %taille mot
    C{1}{k}=mot;
    stat.taille(size(mot,2)) = stat.taille(size(mot,2)) + 1;
%     disp('fin du mot') 
end
disp('fin des calculs')
toc

%% Normalisation
stat.taille =round(100* stat.taille/max(stat.taille));
stat.lettres_1 =round(100* stat.lettres_1/max(max(stat.lettres_1)));
stat.lettres_2 =round(100* stat.lettres_2/max(max(stat.lettres_2)));
stat.start_1 = round(100*stat.start_1/max(stat.start_1));

%% Sauvegarde
save(['stat_', num2str(langue),'.mat'], 'langue', 'stat')

%% Plot les tableaux

% figure(1);bar(stat.taille)%/max(nom(1:40,2)))
% title('Repartition longueurs de mots')
% 
% figure(2); imagesc(stat.lettres_1(65:122,65:122))
% title('Repartition lettre -1')
% 
% figure(3); imagesc(stat.lettres_2(65:122,65:122));
% title('Repartition lettre -2')
% 
% figure(4);bar(stat.start_1);%/max(start1(:,2)))
% title('Repartition des premieres lettres')


%%

dlmwrite(['taille_', langue, '.txt'],stat.taille)
dlmwrite(['lettres_1_', langue, '.txt'],stat.lettres_1)
dlmwrite(['lettres_2_', langue, '.txt'],stat.lettres_2)
dlmwrite(['start_', langue, '.txt'], stat.start_1)

%%
%end