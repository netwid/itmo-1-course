	ORG	0x0	; Инициализация векторов прерываний
V0:	WORD	$VU3
	WORD	0x180
V1:	WORD	$VU2
	WORD	0x180
V2:	WORD	$DEFAULT
	WORD	0x180
V3:	WORD	$DEFAULT
	WORD	0x180
V4:	WORD	$DEFAULT
	WORD	0x180
V5:	WORD	$DEFAULT
	WORD	0x180
V6:	WORD	$DEFAULT
	WORD	0x180
V7:	WORD	$DEFAULT
	WORD	0x180
DEFAULT:	IRET		; В дефолтном случае сразу выйти из прерывания

LB:	WORD	0xFFE6	; Левая граница ОДЗ
RB:	WORD	0x18	; Правая граница ОДЗ
MASK:	WORD	0x1F	; Маска для логического умножения

VU3:	LD	$X	; Обработчик прерывания ВУ-3
	HLT
	NEG
	ASL
	ASL
	SUB	$X
	SUB	#0x7
	OUT	6	; Вывести результат на ВУ-3, сбросить флаг
	HLT
	IRET

VU2:	IN	4	; Обработчик прерывания ВУ-2, сбросить флаг готовности ВУ-2
	LD	$X
	HLT
	AND	MASK
	ST	$X
	HLT
	IRET

START:	CLA		; Очистка AC и запись запрета прерываний на все ВУ кроме 2 и 3
	OUT	0x3
	OUT	0xB
	OUT	0xD
	OUT	0x11
	OUT	0x15
	OUT	0x19
	OUT	0x1D

	LD	#0x8	; Установка прерывания V0 на ВУ-3
	OUT	0x7
	LD	#0x9	; Установка прерывания V1 на ВУ-2
	OUT	0x5

WHILE:	EI		; Разрешение обработки прерываний, начало бесконечного цикла
	DI		; Запрет обработки прерываний перед изменением X
	LD	$X	; Загрузка X
	ADD	#0x2	
	CMP	$RB	; Сравнение с правой границей ОДЗ
	BLT	NEXT	; Если меньше или равно, то пропустить
	BEQ	NEXT
	LD	$LB	; Иначе загрузить левую границу ОДЗ
NEXT:	ST	$X	; Сохранить результат в X
	JUMP	WHILE	

	ORG	0x3D
X:	WORD	0xFFE6	; Результат