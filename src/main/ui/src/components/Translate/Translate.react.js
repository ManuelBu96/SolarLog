/*
 *
 * Copyright 2021 - Manuel Bußmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import de from './de.json';
import am from './am.json';
import zh from './zh.json';
import es from './es.json';
import hi from './hi.json';
import fr from './fr.json';
import gr from './gr.json';
import ru from './ru.json';
import nl from './nl.json';
import pb from './pb.json';
import np from './np.json';
import ge from './ge.json';

const Translate = ({ text, prefLanguage }) => {
  const lang = {
    'de-DE': de,
    'am-AM': am,
    'es-SP': es,
    'zh-CH': zh,
    'hi-IN': hi,
    'pb-IN': pb,
    'np-NP': np,
    'fr-FR': fr,
    'ru-RU': ru,
    'gr-GR': gr,
    'nl-NL': nl,
    'ge-GE': ge,
  };

  const getTranslation = (text) => {
    const defaultPrefLanguage = prefLanguage;
    let translatedText;

    if (defaultPrefLanguage !== 'en-US') {
      if (Object.prototype.hasOwnProperty.call(lang, defaultPrefLanguage)) {
        translatedText = lang[defaultPrefLanguage][text];
      }
    }

    return !translatedText ? text : translatedText;
  };

  return <span> {getTranslation(text)} </span>;
};

Translate.propTypes = {
  text: PropTypes.string,
  prefLanguage: PropTypes.string,
};

function mapStateToProps(store) {
  return {
    prefLanguage: store.settings.prefLanguage,
  };
}

export default connect(mapStateToProps, null)(Translate);
