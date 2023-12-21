# -*- coding: utf-8 -*-
from pypdf import PdfReader, PdfWriter
import psycopg2
import datetime
from datetime import timedelta
import os
def completion(all_lines, field_name, field_data):
    for j in range(len(all_lines)):
        field_data["Text"+field_name[j]] = all_lines[j]
    return field_data

def len_splitting(data, data_len, field_name, field_data):
    words = data.split()
    line = ''
    all_lines = []
    j = 0
    if len(words) != 1:
        for i in range(len(words)):
            if len(line + ' ' + words[i]) <= data_len[j]:
                line += words[i]
                if i != len(words) - 1:
                    line += " "
            else:
                all_lines.append(line)
                line = words[i] + ' '
                j += 1
        if i == len(words) - 1:
             all_lines.append(line)
    else:
        all_lines.append(data)
    return completion(all_lines, field_name, field_data)
def change_field_data(DATA, FIELDS_NAME, FIELDS_LEN, field_data):
    for i in range(len(DATA)):
        try:
            if type(DATA[i]).__name__ == 'list':
                field_data = completion(DATA[i], FIELDS_NAME[i], field_data)
            else:
                if len(DATA[i]) >= 1:
                    field_data = len_splitting(DATA[i], FIELDS_LEN[i], FIELDS_NAME[i], field_data)
        except:
            print(DATA[i])
            print(FIELDS_LEN[i])
            print(FIELDS_NAME[i])

    return field_data

def Patent(DATA, FIELDS_LEN, FIELDS_NAME, field_data):
    filename = "E:\\LETI\\3_kurs\\IT-Projects\\startbusiness\\src\\main\\java\\ru\\studentproject\\startbusiness\\controllers\\Patent.pdf"
    pdf_reader = PdfReader(filename)
    pdf_writer = PdfWriter()
    pdf_writer.append(pdf_reader)
    field_data = change_field_data(DATA, FIELDS_NAME, FIELDS_LEN, field_data)
    for field, value in field_data.items():
        for page in pdf_writer.pages:
            try:
                pdf_writer.update_page_form_field_values(page, {field: value})
            except:
                pass
    new_file_name = "E:\\LETI\\3_kurs\\IT-Projects\\startbusiness\\Patent_" + DATA[0] + ".pdf"
    with open(new_file_name, 'wb') as output_file:
        pdf_writer.write(output_file)
def start_Patent():
    #__Start_DB__
    try:
        conn = psycopg2.connect(dbname='startbusiness', user='business_admin',
                                password='ct0mgB$eYQskZzFg74Qj#QMs', host='79.174.88.15', port='19975')
    except:
        print("ERROR CONNECT DB")
        return 0
    cursor = conn.cursor()

    cursor.execute('SELECT * FROM form WHERE status = 5')
    user_ids = []

    for row in cursor:
        user_ids.append(row)
    cursor.execute('SELECT * FROM employer WHERE form_id =' + str(user_ids[0][0]))
    for row in cursor:
        empl_infs = row
    cursor.execute('SELECT * FROM company WHERE form_id =' + str(user_ids[0][0]))
    for row in cursor:
        comp_infs = row
    cursor.execute('SELECT * FROM subject WHERE id =' + str(comp_infs[13]))
    for row in cursor:
        subj_infs = row


    cursor.execute('SELECT * FROM documents WHERE id>12')
    for row in cursor:
        last_id = row[0]
    insert_query = """ INSERT INTO documents (id, date, file_path, form_id, name, user_id, type)
                                  VALUES (%s, %s, %s, %s, %s, %s, %s)"""
    item_purchase_time = datetime.datetime.now()
    file_path = os.getcwd()+"\\Patent_" + str(empl_infs[6]) + ".pdf"
    item_tuple = (int(last_id) + 1, item_purchase_time, file_path, user_ids[0][0], "Patent_" + str(empl_infs[6]) + ".pdf", 24, 2)
    cursor.execute(insert_query, item_tuple)
    print(item_tuple)
    conn.commit()
    cursor.close()
    conn.close()
    # __End_DB__

    ###___DATA___Patent___
    FULL_NAME = empl_infs[14] + " " + empl_infs[11] + " " + empl_infs[10] # Format: "ВАСИЛЬЕВА АННА ВИКТОРОВНА"
    INN = str(empl_infs[6]).replace("(", "").replace(")", "").replace("-", "").replace(" ", "")  # Format: "783000229243"
    ADRESS_S_RF = str(comp_infs[13]) # Format: "78"
    ADRESS_1 = str(subj_infs[2]) # Format: "3"
    ADRESS_1_NAME = str(comp_infs[5]) # Format: "МО #65"
    ADRESS_2 = ""
    ADRESS_2_NAME = ""
    ADRESS_3 = ""
    ADRESS_3_NAME = ""
    ADRESS_4 = ""
    ADRESS_4_NAME = ""
    try:
        adr = str(comp_infs[8]).rfind(".")
    except:
        adr = str(comp_infs[8]).find(" ")
    ADRESS_5 = str(comp_infs[8])[0:adr+1] # Format: "ПР.-КТ"
    ADRESS_5_NAME = str(comp_infs[8])[adr+1::] # Format: "БОГАТЫРСКИЙ"
    # ...
    ADRESS_PLACE_1 = "д. " + str(comp_infs[2])  # Format: "д. 59|к. 1|к. 1"
    ADRESS_PLACE_2 = "офис " + str(comp_infs[6])   # Format: "кв. 21"
    ADRESS_PLACE_3 = ""  # Format: "кв. 59"
    MAIN_CODE = comp_infs[10].replace(".", "")  # Format: "10 78 71"
    CUR_DATA = user_ids[0][1].strftime('%d.%m.%Y').split(".")
    NEXT_DATA = (user_ids[0][1] + timedelta(days=31)).strftime('%d.%m.%Y').split(".")
    ###___DATA___IP___
    ADRS = [["", ""], ["", ""], ["", ""]]
    if len(ADRESS_PLACE_1) != 0 and 1 == 0:
        for i in range(len(ADRESS_PLACE_1.split("|"))):
            ADRS[i] = ADRESS_PLACE_1.split("|")[i].split()
    else:
        ADRS = [[ADRESS_PLACE_1.split()[0], str(ADRESS_PLACE_1.split()[1])], ["", ""], ["", ""]]
    if len(ADRESS_PLACE_2) != 0:
        ADRESS_PLACE_2_1 = ADRESS_PLACE_2.split()[0]
        ADRESS_PLACE_2_2 = ADRESS_PLACE_2.split()[1]
    else:
        ADRESS_PLACE_2_1 = ""
        ADRESS_PLACE_2_2 = ""
    if len(ADRESS_PLACE_3) != 0:
        ADRESS_PLACE_3_1 = ADRESS_PLACE_3.split()[0]
        ADRESS_PLACE_3_2 = ADRESS_PLACE_3.split()[1]
    else:
        ADRESS_PLACE_3_1 = ""
        ADRESS_PLACE_3_2 = ""
    okveds = {"8122": "ДЕЯТЕЛЬНОСТЬ ПО ОЧИСТКЕ И УБОРКЕ ЖИЛЫХ ЗДАНИЙ И НЕЖИЛЫХ ПОМЕЩЕНИЙ ПРОЧАЯ",
              "6831": "Деятельность агентств недвижимости за вознаграждение или на договорной основе",
              "1071": "Производство хлеба и мучных кондитерских изделий, тортов и пирожных недлительного хранения",
              "6510": "Страхование, перестрахование, деятельность негосударственных пенсионных фондов, кроме обязательного социального обеспечения"}
    DATA_5 = [
        INN,
        "7802",
        FULL_NAME.split()[0],
        FULL_NAME.split()[1],
        FULL_NAME.split()[2],
        [CUR_DATA[0], CUR_DATA[1], CUR_DATA[2]],
        [NEXT_DATA[0], NEXT_DATA[1], NEXT_DATA[2]],
        "3--",
        okveds[str(MAIN_CODE[0:2]+MAIN_CODE[4::])],
        MAIN_CODE,
        "1",
        "06",
        "6",
        "003",
        ADRESS_S_RF,
        "7802",
        ADRESS_S_RF,
        ADRESS_1,
        ADRESS_1_NAME,
        ADRESS_5,
        ADRESS_5_NAME,
        ADRESS_PLACE_1.split()[0],
        ADRESS_PLACE_1.split()[1],
        ADRESS_PLACE_2.split()[0],
        ADRESS_PLACE_2.split()[1],
        "",
        ""
    ]
    FIELDS_LEN_5 = [
        [12],
        [4],
        [35],
        [35],
        [35],
        [2, 2, 4],
        [2, 2, 4],
        [3],
        [40, 40, 40, 40, 40, 40],
        [6],
        [1],
        [2],
        [1],
        [3],
        [2],
        [4],
        [2],
        [1],
        [17, 40],
        [10],
        [17, 40],
        [10],
        [17],
        [10],
        [17],
        [3],
        [3]
    ]

    FIELDS_NAME_5 = [
        ["1"],
        ["2"],
        ["3.0"],
        ["3.1"],
        ["3.2"],
        ["11.0.0", "11.1.0", "12.0"],
        ["11.0.1", "11.1.1", "12.1"],
        ["13.0"],
        ["21.0.0", "21.0.1", "21.0.2", "21.0.3", "21.0.4", "21.0.5"],
        ["22"],
        ["23.0"],
        ["24"],
        ["23.1"],
        ["25"],
        ["26.0"],
        ["26.1"],
        ["28"],
        ["27.0"],
        ["30.0", "29.0"],
        ["50.2"],
        ["30.1.3", "29.1.3", "29.1.4"],
        ["50.3"],
        ["30.1.5"],
        ["50.6"],
        ["30.1.7.1"],
        ["31"],
        ["37"]
    ]
    Patent(DATA_5, FIELDS_LEN_5, FIELDS_NAME_5, {})
start_Patent()
