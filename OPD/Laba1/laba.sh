#!/bin/bash
# 414

# Clear
if [[ -n "$1" ]]
then
    chmod u=rwx -R .
    rm -r $(ls -I '*.sh')
    exit 1
fi

# 1 task
touch dusclops3
mkdir golbat3
mkdir golbat3/typhlosion
mkdir golbat3/paras
touch golbat3/remoraid
touch golbat3/krokorok
touch golbat3/elekid
mkdir munchlax5
mkdir munchlax5/skuntank
touch munchlax5/snubbull
mkdir munchlax5/raichu
touch munchlax5/fraxure
mkdir munchlax5/dustox
mkdir munchlax5/drowzee
touch tranquill0
touch trubbish8
mkdir vigoroth4
touch vigoroth4/ledyba
mkdir vigoroth4/rhyhorn
touch vigoroth4/chinchou
touch vigoroth4/houndour

echo 'Тип покемона GHOST NONE' > dusclops3
echo 'Живет
Ocean' > golbat3/remoraid
echo 'Развитые способности Anger Point' > golbat3/krokorok
echo 'Тип покемона
ELECTRIC NONE' > golbat3/elekid
echo 'Tun диеты Carnivore' > munchlax5/snubbull
echo 'Способности
Leer Assurance Dragon Rage Double Chop Scary Face Slash False Swipe
Dragon Claw Dragon Dance Taunt Dragon Pulse Swords Dance Guillotine
Outrage - Giga Impact' > munchlax5/fraxure
echo 'Способности Growl Leer Ouick Attack
Air Cutter Roost Detect Taunt Air Slash Razor wind Featherdance
Swagger Facade Tailwind Sky Attack' > tranquill0
echo 'Возможности Overland=5
Surface=4 Jump=1 Power=2 Intelligence=4 Inflatable=0' > trubbish8
echo 'satk=4
sdef=8 spd=6' > vigoroth4/ledyba
echo 'Возможности Overland=2 Surface=8 Underwater=8
Jump=2 Power=1 Intelligence=3 Gilled=0 Glow=O' > vigoroth4/chinchou

# 2 task
chmod 640 dusclops3
chmod 333 golbat3
chmod 764 golbat3/typhlosion
chmod 573 golbat3/paras
chmod 006 golbat3/remoraid
chmod 640 golbat3/krokorok
chmod 620 golbat3/elekid
chmod 315 munchlax5
chmod 537 munchlax5/skuntank
chmod 444 munchlax5/snubbull
chmod 512 munchlax5/raichu
chmod 400 munchlax5/fraxure
chmod 752 munchlax5/dustox
chmod 570 munchlax5/drowzee
chmod 046 tranquill0
chmod 006 trubbish8
chmod 512 vigoroth4
chmod 620 vigoroth4/ledyba
chmod 335 vigoroth4/rhyhorn
chmod 044 vigoroth4/chinchou
chmod 440 vigoroth4/houndour

# 3 task
chmod u+r trubbish8
chmod u+w vigoroth4
cp trubbish8 vigoroth4/ledybatrubbish
chmod u-w vigoroth4
chmod u-r trubbish8

chmod u+w golbat3/paras
chmod u+r golbat3 golbat3/remoraid
cp -r golbat3 golbat3/paras
chmod u-r golbat3/remoraid golbat3
chmod u-w golbat3/paras

ln -s trubbish8 munchlax5/fraxuretrubbish
cat golbat3/krokorok vigoroth4/ledyba > tranquill0_11
ln tranquill0 golbat3/remoraidtranquill

chmod u+r trubbish8
chmod u+w munchlax5/drowzee
cp trubbish8 munchlax5/drowzee
chmod u-w munchlax5/drowzee
chmod u-r trubbish8

ln -s vigoroth4 Copy_26

# 4 task
echo '1)'
wc -l dusclops3 > /tmp/1_stdout 2>&1
echo '2)'
ls -l ./{,*/,*/*/,*/*/*/,*/*/*/*/}r* 2> /tmp/2_stderr | tail -2 | sort -k 2
echo '3)'
cat -n vigoroth4/* 2> /tmp/3_stderr | grep -ei '[^k]$'
echo '4)'
ls -l ./{,*/,*/*/,*/*/*/,*/*/*/*/} 2> /dev/null | grep 're' | head -4 | sort -nrk 5
echo '5)'
cat vigoroth4/* 2> /dev/null | grep -ei 'k$'    
echo '6)'
wc -l ./{,*/,*/*/,*/*/*/,*/*/*/*/}t* 2> /tmp/6_stderr | sort -nk 1

# 5 task
rm -f tranquill0
rm -f golbat3/krokorok
rm -f Copy_*
rm -f golbat3/remoraidtranqui*
rm -rf golbat3
rm -rf golbat3/paras
