/*
 * Copyright 2019 vmadalin.com
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
 */

package cn.mtjsoft.www.baseres.extentions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
fun <VM : ViewModel> Fragment.viewModel(
    key: String? = null,
    factory: () -> VM
): VM {
    val factoryViewModel = factory()
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return factoryViewModel as T
        }
    }

    return if (key != null) {
        ViewModelProvider(this, viewModelProviderFactory).get(key, factoryViewModel::class.java)
    } else {
        ViewModelProvider(this, viewModelProviderFactory).get(factoryViewModel::class.java)
    }
}
